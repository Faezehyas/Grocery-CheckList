package faezeh.yas.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import faezeh.yas.shoppinglist.R
import faezeh.yas.shoppinglist.data.db.ShoppingDataBase
import faezeh.yas.shoppinglist.data.db.entities.ShoppingItem
import faezeh.yas.shoppinglist.data.repositories.ShoppingRepository
import faezeh.yas.shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*


class ShoppingActivity : AppCompatActivity() {

    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ShoppingList)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDataBase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

        ivlogo.setOnClickListener {
            Toast.makeText(this.applicationContext, "Have a Good Shopping ^_^", Toast.LENGTH_SHORT).show()
        }

    }
}