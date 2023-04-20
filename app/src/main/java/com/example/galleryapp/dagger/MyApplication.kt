package com.example.currency.dagger

import android.app.Application
import com.alatheer.dagger.ApiModule
import com.alatheer.dagger.AppModule
import com.example.currency.constants.Constants
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication :Application() {
    /*@Inject
    lateinit var apiModule: ApiModule*/
    /*lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .apiModule(ApiModule(Constants.url))
            .appModule( AppModule(this))
            .build()
    }
    fun getAppComponent(): AppComponent {
        return component
    }*/
}