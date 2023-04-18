package com.example.galleryapp.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currency.constants.Constants
import com.example.galleryapp.api.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HomeViewModel :ViewModel() {
    var galleryMutableLiveData: MutableLiveData<GalleryModel> = MutableLiveData<GalleryModel>()
    var allgalleryMutableLiveData: MutableLiveData<GalleryModel> = MutableLiveData<GalleryModel>()
    fun search_in_gallery(search: String, page: Int, api: Api) {
        val observable: Observable<GalleryModel> = api.get_gallery(Constants.key,search,page)
            .subscribeOn(Schedulers.io())
            .debounce(6,TimeUnit.SECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(
            { o: GalleryModel? -> galleryMutableLiveData.value = o },
            { e: Throwable -> Log.e("rrrr", e.message.toString()) })
    }

    fun PerformPagination(search: String, page: Int, api: Api) {
        val observable: Observable<GalleryModel> = api.get_gallery(Constants.key,search,page)
            .subscribeOn(Schedulers.io())
            .debounce(6,TimeUnit.SECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(
            { o: GalleryModel? -> if (o!!.page<= o.total_results){
                galleryMutableLiveData.value = o
            }
            },
            { e: Throwable -> Log.e("mmmm", e.message.toString()) })
    }

}