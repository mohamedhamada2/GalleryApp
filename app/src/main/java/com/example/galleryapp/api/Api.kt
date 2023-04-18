package com.example.galleryapp.api

import com.example.galleryapp.fragments.home.GalleryModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {
    @GET("search")
    fun get_gallery(@Header("Authorization")key:String,@Query("query")query:String,@Query("per_page")page: Int): Observable<GalleryModel>
}