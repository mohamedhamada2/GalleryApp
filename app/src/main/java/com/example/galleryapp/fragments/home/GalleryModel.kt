package com.example.galleryapp.fragments.home

data class GalleryModel(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)