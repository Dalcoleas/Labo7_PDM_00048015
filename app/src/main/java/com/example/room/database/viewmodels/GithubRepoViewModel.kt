package com.example.room.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.database.RoomDB
import com.example.room.database.entities.GithubRepo
import com.example.room.database.repositories.GithubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubRepoViewModel(app:Application):AndroidViewModel(app) {

    private val repository:GithubRepoRepository

    init{
        val repoDao = RoomDB.getInstance(app).repoDAO()
        repository = GithubRepoRepository(repoDao)
    }

    fun getAll():LiveData<List<GithubRepo>> = repository.getAll()

    fun insert(repo: GithubRepo) = viewModelScope.launch(Dispatchers.IO) {//se lanza la corrutina scope: el que va a ejecutar la corrutina es decir, se despacha hilos
        repository.insert(repo)
    }
}