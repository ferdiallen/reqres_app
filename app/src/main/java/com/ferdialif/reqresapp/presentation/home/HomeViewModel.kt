package com.ferdialif.reqresapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ferdialif.reqresapp.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PersonRepository
) : ViewModel() {
    val personData = repository.getPerson().cachedIn(viewModelScope)
}