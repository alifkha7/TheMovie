package com.hirocode.themovie.core.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hirocode.themovie.core.data.source.remote.network.ApiService
import com.hirocode.themovie.core.data.source.remote.response.ReviewsItem

class ReviewsPagingSource(private val apiService: ApiService, private val movieId: Int?) : PagingSource<Int, ReviewsItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewsItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getReviews(movieId, "6d754783e28255649713de5092888da4", page)

            LoadResult.Page(
                data = responseData.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.results.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReviewsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}