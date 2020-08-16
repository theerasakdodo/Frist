package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ContectList::class), version = 1, exportSchema = false)
public abstract class ContectListDatabase : RoomDatabase() {

    abstract fun contectListDatabaseDao(): ContectListDatabaseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ContectListDatabase? = null

        fun getDatabase(context: Context): ContectListDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContectListDatabase::class.java,
                        "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}