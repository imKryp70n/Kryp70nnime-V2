package com.imkryp70n.kryp70nnime.view.fragments.discover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.di.AesteticDialog
import com.imkryp70n.kryp70nnime.di.Injection
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.discovery.ResultsItem
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel
import com.imkryp70n.kryp70nnime.viewmodel.discover.DiscoverViewModel

class DiscoveryFragment : Fragment() {

    val TAG = "CONSOLE"

    private val viewModel by viewModels<DiscoverViewModel> {
        Injection.provideMainViewModelFactory()
    }


    lateinit var adapterDiscover: DiscoverAdapter
    lateinit var adapterTrending : TrendingAdapter


    lateinit var rvAnimeList : RecyclerView
    lateinit var tvAnimeTrending : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        rvAnimeList = view.findViewById(R.id.rvAnimeList)
        tvAnimeTrending = view.findViewById(R.id.rvAnimeTrendingList)

    }

    private fun setupViewModel() {
        viewModel.discoveries.observe(viewLifecycleOwner, renderDiscover)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver as Observer<in Any?>)
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)

        // Trending
        viewModel.getTrending.observe(viewLifecycleOwner, renderTrending)
        viewModel.isViewLoadingTrending.observe(viewLifecycleOwner, isViewLoadingTrendingObserver)
        viewModel.onTrendingMessageError.observe(viewLifecycleOwner, onTrendingErrorObserver as Observer<in Any?>)
        viewModel.isTrendingEmptyList.observe(viewLifecycleOwner, emptyListTrendingObserver)


    }


    //observers
    private val renderDiscover = Observer<DiscoveryModel>  {

        adapterDiscover = DiscoverAdapter(viewModel.discoveries.value!!.results!!)
        // horizontal
        rvAnimeList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvAnimeList.adapter = adapterDiscover
        adapterDiscover.update(it.results!!)
    }

    private val isViewLoadingObserver = Observer<Boolean> {

    }

    private val onMessageErrorObserver = Observer<Any> {
        AesteticDialog.toasterError(requireContext(), false,5000, "Error", it.toString())
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.d(TAG, "emptyListObserver: $it")
    }



    // Trending Observers
    private val renderTrending = Observer<TrendingModel>  {


        adapterTrending = TrendingAdapter(viewModel.getTrending.value!!.results!!)
        // Horizontal
        tvAnimeTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvAnimeTrending.adapter = adapterTrending
        adapterTrending.update(it.results!!)
    }
    private val isViewLoadingTrendingObserver = Observer<Boolean> {
        Log.d(TAG, "isViewLoadingObserver: $it")
    }

    private val onTrendingErrorObserver = Observer<Any> {
        AesteticDialog.toasterError(requireContext(), false,5000, "Error", it.toString())
    }

    private val emptyListTrendingObserver = Observer<Boolean> {
        Log.d(TAG, "emptyListObserver: $it")
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadDiscover()
        viewModel.loadTrending()
    }

    override fun onDestroy() {
        super.onDestroy()
        Injection.destroy()
    }


}