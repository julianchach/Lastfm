package com.example.fmmusic.viewModels

import androidx.lifecycle.ViewModel
import com.example.fmmusic.repository.DbRepository
import com.example.fmmusic.repository.Repository

class ServiceViewModel(var dbRepository: DbRepository, var repository: Repository) : ViewModel() {

}