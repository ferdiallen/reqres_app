package com.ferdialif.reqresapp.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdialif.reqresapp.domain.api.PersonData
import com.ferdialif.reqresapp.domain.model.Data
import com.ferdialif.reqresapp.domain.model.PersonModel
import javax.inject.Inject

class PersonPagingSource @Inject constructor(private val data: PersonData) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 1
            val response = data.getPersonData(page)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.minus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}