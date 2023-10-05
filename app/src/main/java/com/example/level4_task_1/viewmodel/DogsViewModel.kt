package com.example.level4_task_1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.level4_task_1.data.api.util.Resource
import com.example.level4_task_1.data.model.Cat
import com.example.level4_task_1.data.model.Dog
import com.example.level4_task_1.repository.CatsRepository
import com.example.level4_task_1.repository.DogsRepository
import kotlinx.coroutines.launch

class DogsViewModel (application: Application) : AndroidViewModel(application) {
    private val catsRepository = DogsRepository()

    val dogsResource: LiveData<Resource<Dog>>
        get() = _dogsResource

    private val _dogsResource: MutableLiveData<Resource<Dog>> = MutableLiveData(Resource.Empty())

    fun getDog() {
        //set resource type to loading
        _dogsResource.value = Resource.Loading()

        viewModelScope.launch {
            _dogsResource.value = catsRepository.getRandomDog()
        }
    }
}