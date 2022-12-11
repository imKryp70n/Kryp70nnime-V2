package com.imkryp70n.kryp70nnime.view.fragments.history

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
import androidx.room.Room
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.data.database.Bookmark
import com.imkryp70n.kryp70nnime.data.database.BookmarkDatabase
import com.imkryp70n.kryp70nnime.di.AesteticDialog
import com.imkryp70n.kryp70nnime.di.Injection
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.view.fragments.discover.DiscoverAdapter
import com.imkryp70n.kryp70nnime.viewmodel.bookmark.BookmarkViewModel
import com.imkryp70n.kryp70nnime.viewmodel.discover.DiscoverViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HistoryFragment : Fragment() {
    val TAG = "CONSOLE"

    private val viewModel by viewModels<BookmarkViewModel> {
        Injection.provideBookmarkViewModelFactory(context)
    }

    lateinit var db: BookmarkDatabase
    lateinit var recyclerView: RecyclerView

    lateinit var adapter : HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        recyclerView = view.findViewById(R.id.bookmarkRecycle)
    }

    private fun setupViewModel() {
        viewModel.bookmarks.observe(viewLifecycleOwner, renderBookmark)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner,
            onMessageErrorObserver as Observer<in Any?>
        )
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)

    }

    private val renderBookmark = Observer<List<Bookmark>> {
        adapter = HistoryAdapter(
            viewModel.bookmarks.value!!
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }

    private val isViewLoadingObserver = Observer<Boolean> {

    }

    private val onMessageErrorObserver = Observer<Any> {
        AesteticDialog.toasterError(requireContext(), false, 5000, "Error", it.toString())
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.d(TAG, "emptyListObserver: $it")
    }



    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            viewModel.getBookmarks()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injection.destroy()
    }
}