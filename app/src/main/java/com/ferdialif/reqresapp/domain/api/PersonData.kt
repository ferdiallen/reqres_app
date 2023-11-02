package com.ferdialif.reqresapp.domain.api

import com.ferdialif.reqresapp.domain.model.PersonModel

interface PersonData {
     suspend fun getPersonData(page:Int) : PersonModel
}