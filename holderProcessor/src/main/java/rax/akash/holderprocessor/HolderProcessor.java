package rax.akash.holderprocessor;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import rax.akash.api.AutoHolder;
import rax.akash.api.ViewType;

/* Need some Refactoring*/
@AutoService(Processor.class)
public class HolderProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;
    private Elements elements;
    private TypeElement typeElement;
    private String packageName;

    private static final ClassName view = ClassName.get("android.view", "View");


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elements = processingEnvironment.getElementUtils();

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        try {
            /**
             * 1- Find all annotated element and Generate class
             */
            for (Element element : roundEnvironment.getElementsAnnotatedWith(AutoHolder.class)) {

                if (element.getKind() != ElementKind.CLASS) {
                    messager.printMessage(Diagnostic.Kind.ERROR, "Can be applied to class.");
                    return true;
                }
                typeElement = (TypeElement) element;

                String className = typeElement.getSimpleName().toString();
                packageName = elements.getPackageOf(typeElement).getQualifiedName().toString();
                AutoHolder viewInfo = typeElement.getAnnotation(AutoHolder.class);

                ClassName recyclerView = ClassName.get("android.support.v7.widget", "RecyclerView");
                TypeSpec.Builder navigatorClass = TypeSpec
                        .classBuilder(className + "Vh")
                        .superclass(ClassName.get("android.support.v7.widget", "RecyclerView.ViewHolder"))
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
                MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(view, "view")
                        .addStatement("super(view)");

                navigatorClass.addField(recyclerView, "recyclerView");

                for (ViewType info : viewInfo.views()) {

                    try {
                        navigatorClass.addField(ClassName.get(info.type()), info.name());
                    } catch (MirroredTypeException mte) {
                        navigatorClass.addField(ClassName.get(mte.getTypeMirror()), info.name());
                    }

                    constructor.addStatement(info.name() + " = view.findViewById(" + info.id() + ")");
                }


                navigatorClass.addMethod(constructor.build());
                JavaFile.builder(packageName, navigatorClass.build())
                        .build().writeTo(filer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(AutoHolder.class.getCanonicalName());
    }
}
