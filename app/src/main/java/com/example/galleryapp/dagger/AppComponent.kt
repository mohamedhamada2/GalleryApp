package com.example.currency.dagger

import com.alatheer.dagger.ApiModule
import com.alatheer.dagger.AppModule
import com.example.galleryapp.MainActivity
import com.example.galleryapp.fragments.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ApiModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
}