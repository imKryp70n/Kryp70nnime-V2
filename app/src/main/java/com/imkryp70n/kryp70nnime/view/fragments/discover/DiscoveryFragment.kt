package com.imkryp70n.kryp70nnime.view.fragments.discover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.di.Injection
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.viewmodel.discover.DiscoverViewModel

class DiscoveryFragment : Fragment() {

    val TAG = "CONSOLE"

    private val viewModel by viewModels<DiscoverViewModel> {
        Injection.provideMainViewModelFactory()
    }


    lateinit var adapter: DiscoverAdapter
    lateinit var rvAnimeList : RecyclerView

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

    }

    private fun setupViewModel() {
        viewModel.discoveries.observe(viewLifecycleOwner, renderDiscover)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver as Observer<in Any?>)
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)
    }


    //observers
    private val renderDiscover = Observer<DiscoveryModel>  {
        Log.d(TAG, "renderDiscover: $it")
        adapter = DiscoverAdapter()
        adapter.setData(it.results!!)
        // horizontal
        rvAnimeList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvAnimeList.adapter = adapter
        adapter.update(it.results)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.d(TAG, "isViewLoadingObserver: $it")
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.d(TAG, "onMessageErrorObserver: $it")
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.d(TAG, "emptyListObserver: $it")
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadDiscover()
    }

    override fun onDestroy() {
        super.onDestroy()
        Injection.destroy()
    }


}