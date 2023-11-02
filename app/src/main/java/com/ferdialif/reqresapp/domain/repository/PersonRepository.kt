package com.ferdialif.reqresapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ferdialif.reqresapp.domain.api.PersonData
import com.ferdialif.reqresapp.domain.paging.PersonPagingSource
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personData: PersonData
) {
    fun getPerson() = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = {
            PersonPagingSource(personData)
        }
    ).flow
}