package com.example.android.navigation

import androidx.lifecycle.LiveData
import database.ContectListDatabaseDao
import database.ContectList

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

class ContectListRepository(private val contectListDatabaseDao: ContectListDatabaseDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<ContectList>> = contectListDatabaseDao.getAlphabetizedWords()

    suspend fun insert(word: ContectList) {
        contectListDatabaseDao.insert(word)
    }
}