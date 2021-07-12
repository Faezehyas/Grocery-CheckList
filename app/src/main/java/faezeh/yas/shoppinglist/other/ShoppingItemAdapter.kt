package faezeh.yas.shoppinglist.other

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import faezeh.yas.shoppinglist.R
import faezeh.yas.shoppinglist.data.db.entities.ShoppingItem
import faezeh.yas.shoppinglist.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_layout.view.*


class ShoppingItemAdapter(
    var items : List<ShoppingItem>,
    private val viewModel : ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_layout, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.itemView.tvName.text = currentShoppingItem.name
        holder.itemView.tvAmount.text = "${currentShoppingItem.amount}"

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
            Toast.makeText(it.context, "item ${currentShoppingItem.name} deleted.", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if(currentShoppingItem.amount > 0){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    inner class ShoppingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}
