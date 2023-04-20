package com.example.galleryapp.dagger

import android.content.Context
import android.util.Log
import com.example.currency.constants.Constants
import com.example.galleryapp.MainActivity
import com.example.galleryapp.api.Api
import com.example.galleryapp.fragments.home.GalleryModel
import com.example.galleryapp.fragments.home.HomeFragment
import com.example.galleryapp.fragments.home.ImagesAdapter
import com.example.galleryapp.fragments.home.Photo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object HomeViewModelModule {

    @ViewModelScoped
    @Provides
    fun provide_page():Int {
        return 1
    }
    @ViewModelScoped
    @Provides
    fun provide_search():String {
       return "dogs"
    }
    /*@ViewModelScoped
    @Provides
    fun provide_search2(homeFragment: HomeFragment):String {
       return homeFragment.search;
    }*/


}