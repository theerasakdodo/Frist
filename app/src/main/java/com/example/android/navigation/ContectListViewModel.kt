package com.example.android.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import database.ContectList
import database.ContectListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContectListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContectListRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<ContectList>>

    init {
        val contectListDatabaseDao = ContectListDatabase.getDatabase(application).contectListDatabaseDao()
        repository = ContectListRepository(contectListDatabaseDao)
        allWords = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(contectList: ContectList) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contectList)
    }
}