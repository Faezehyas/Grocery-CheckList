package faezeh.yas.shoppinglist.ui

import faezeh.yas.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item : ShoppingItem)
}