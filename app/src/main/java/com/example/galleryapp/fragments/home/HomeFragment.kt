package com.example.galleryapp.fragments.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.dagger.MyApplication
import com.example.galleryapp.R
import com.example.galleryapp.api.Api
import com.example.galleryapp.dagger.HomeViewModelModule
import com.example.galleryapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment() {
    val homeViewModel: HomeViewModel by viewModels()
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var search:String
    @Inject
    lateinit var retrofit: Retrofit
    lateinit var api: Api
    lateinit var layoutManager: LinearLayoutManager

    private var isloading = false
    private var pastvisibleitem = 0
    private  var visibleitemcount = 0
    private  var totalitemcount= 0
    private  var previous_total = 0
    var view_threshold  = 15
    var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = fragmentHomeBinding.root;
        //homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        //homeViewModel = HomeViewModel(requireContext(),this)
        //(activity?.application as MyApplication).getAppComponent()!!.inject(this)
        api = retrofit.create(Api::class.java)
        layoutManager = LinearLayoutManager(activity)

        /*fragmentHomeBinding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })*/
        fragmentHomeBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                search = p0.toString();
                page = 1
                pastvisibleitem = 0
                visibleitemcount = 0
                totalitemcount= 0
                previous_total = 0
                view_threshold  = 15
                //search = fragmentHomeBinding.etSearch.text.toString()
                homeViewModel.search_in_gallery()
                fragmentHomeBinding.imagesRecycler.addOnScrollListener(object :
                    RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        visibleitemcount = layoutManager.childCount
                        totalitemcount = layoutManager.itemCount
                        pastvisibleitem = layoutManager.findFirstVisibleItemPosition()
                        if (dy>0) {
                            if (isloading) {
                                if (totalitemcount > previous_total) {
                                    isloading = false
                                    previous_total = totalitemcount
                                }
                            }
                            if (!isloading && totalitemcount - visibleitemcount <= pastvisibleitem + view_threshold) {
                                page++
                                homeViewModel.load_more_search_in_gallery()
                                isloading = true
                            }
                        }else{
                        }
                    }
                })
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        /*homeViewModel.galleryMutableLiveData.observe(viewLifecycleOwner, Observer {

            setRecyclerView(it.photos)
        })*/
        homeViewModel.galleryadapterLiveData.observe(viewLifecycleOwner, Observer {
            setRecyclerView(it)
        })

        return view
    }

     fun setRecyclerView(imagesAdapter: ImagesAdapter) {
        fragmentHomeBinding.imagesRecycler.setHasFixedSize(true)
        fragmentHomeBinding.imagesRecycler.layoutManager = layoutManager
        fragmentHomeBinding.imagesRecycler.adapter = imagesAdapter

    }
}
