package faezeh.yas.shoppinglist.data.repositories

import faezeh.yas.shoppinglist.data.db.ShoppingDataBase
import faezeh.yas.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(private val db : ShoppingDataBase) {

    suspend fun upsert(item : ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item : ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}