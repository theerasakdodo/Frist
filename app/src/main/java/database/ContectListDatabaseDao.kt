package database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContectListDatabaseDao {


    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<ContectList>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: ContectList)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}