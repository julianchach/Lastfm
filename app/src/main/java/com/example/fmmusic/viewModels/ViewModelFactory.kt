package com.example.fmmusic.viewModels

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fmmusic.repository.DbRepository
import com.example.fmmusic.repository.Repository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(var dbRepository: DbRepository, var repository: Repository) : ViewModelProvider.Factory {

    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            return ServiceViewModel(dbRepository, repository) as T
        }
        throw IllegalArgumentException("Unknow class name")
    }

}