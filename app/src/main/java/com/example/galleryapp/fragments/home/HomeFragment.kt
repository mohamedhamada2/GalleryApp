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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currency.dagger.MyApplication
import com.example.galleryapp.R
import com.example.galleryapp.api.Api
import com.example.galleryapp.databinding.FragmentHomeBinding
import retrofit2.Retrofit
import javax.inject.Inject

class HomeFragment : Fragment() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var search:String
    @Inject
    lateinit var retrofit: Retrofit
    lateinit var api: Api
    lateinit var gridLayoutManager: LinearLayoutManager
    lateinit var imagesAdapter: ImagesAdapter
    var isloading :Boolean = false
    private var pastvisibleitem = 0
    private  var visibleitemcount:Int = 0
    private  var totalitemcount:Int = 0
    private  var previous_total:Int = 0
    var view_threshold = 1
    var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = fragmentHomeBinding.root;
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        (activity?.application as MyApplication).getAppComponent()!!.inject(this)
        api = retrofit.create(Api::class.java)
        gridLayoutManager = LinearLayoutManager(activity)
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
                visibleitemcount = gridLayoutManager.getChildCount();
                totalitemcount = gridLayoutManager.getItemCount();
                pastvisibleitem = gridLayoutManager.findFirstVisibleItemPosition();
                search = p0.toString();
                //search = fragmentHomeBinding.etSearch.text.toString()
                homeViewModel.search_in_gallery(search, 1, api)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        homeViewModel.galleryMutableLiveData.observe(viewLifecycleOwner, Observer {
            imagesAdapter = ImagesAdapter(it.photos as ArrayList<Photo>, requireContext())
            setRecyclerView(imagesAdapter)
            if (isloading) {
                if (totalitemcount > previous_total) {
                    isloading = false;
                    previous_total = totalitemcount;
                }
            }
            if (!isloading && (totalitemcount - visibleitemcount) <= pastvisibleitem + view_threshold) {
                page++;
                homeViewModel.PerformPagination(search, 2, api);
                Toast.makeText(activity,"success",Toast.LENGTH_LONG).show()
                isloading = true;
            }

        })
        homeViewModel.galleryMutableLiveData.observe(viewLifecycleOwner, Observer {
            imagesAdapter.add_photo(it.photos as ArrayList<Photo>);
            setRecyclerView(imagesAdapter)
        })
        return view
    }

    private fun setRecyclerView(imagesAdapter:ImagesAdapter) {
       // imagesAdapter = ImagesAdapter(photos, requireContext())
        fragmentHomeBinding.imagesRecycler.setHasFixedSize(true)
        fragmentHomeBinding.imagesRecycler.layoutManager = gridLayoutManager
        fragmentHomeBinding.imagesRecycler.adapter = imagesAdapter

    }

    private fun performSearch() {
        search = fragmentHomeBinding.etSearch.text.toString()
        homeViewModel.search_in_gallery(search,1,api)

    }
}