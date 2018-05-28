package rax.akash.api;

/**
 * Created by Akash Saggu(R4X) on 28-05-2018 at 16:31.
 * akashsaggu@protonmail.com
 */
public @interface ViewType {

    /**
     * The type of the View
     */
    Class<?> type();

    /**
     * The R.id. of the view widget
     */
    int id();

    /**
     * The name of the field
     */
    String name();
}
