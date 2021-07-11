package faezeh.yas.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import faezeh.yas.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDataBase : RoomDatabase(){


    abstract fun getShoppingDao() : ShoppingDao


    companion object {

        @Volatile
        private var instance : ShoppingDataBase? = null

        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context : Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDataBase::class.java,
                "ShoppingDB.db")
                .build()
    }


}