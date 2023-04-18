package com.example.galleryapp.api

import androidx.paging.PagingData
import com.example.galleryapp.fragments.home.GalleryModel
import com.example.galleryapp.fragments.home.Photo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {
    @GET("search")
    fun get_gallery(@Header("Authorization")key:String,@Query("query")query:String,@Query("page")page: Int): Observable<GalleryModel>
}