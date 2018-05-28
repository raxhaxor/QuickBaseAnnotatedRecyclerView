package rax.akash.quickbaserecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import rax.akash.api.AutoHolder
import rax.akash.quickbaserecyclerview.adapter.AutoHolderAdapter
import rax.akash.quickbaserecyclerview.model.Name

@AutoHolder
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val autoHolderAdapter = AutoHolderAdapter()
        home_list.adapter = autoHolderAdapter

        autoHolderAdapter.addNewList(arrayListOf(Name("Akash"),Name("Saggu"),Name("Rax")))
    }
}
