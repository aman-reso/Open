package `in`.book.repo.open.ui.topic_list

import `in`.book.repo.open.R
import `in`.book.repo.open.adapter.chapterList.ChapterListAdapter
import `in`.book.repo.open.custom.PaginationScrollListener
import `in`.book.repo.open.databinding.ActivityTopicListBinding
import `in`.book.repo.open.models.chapterList.ChapterListResponse
import `in`.book.repo.open.network.ResultWrapper
import `in`.book.repo.open.setViewGone
import `in`.book.repo.open.setViewVisible
import `in`.book.repo.open.ui.topic_list.interfaces.TopicListingIntentHandler
import `in`.book.repo.open.viewmodel.chapterList.ChapterListViewModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val INTENT_KEY_BOOK_ID = "intent_key_book_id"
const val INTENT_KEY_CHAPTER_INDEX = "intent_key_chapter_index"

@AndroidEntryPoint
class TopicListActivity : AppCompatActivity(), TopicListingIntentHandler {

    private var binding: ActivityTopicListBinding? = null

    private val chapterListViewModel: ChapterListViewModel? by viewModels()
    private var recyclerView: RecyclerView? = null

    private val chapterListAdapter: ChapterListAdapter by lazy { ChapterListAdapter() }
    private var setUpImageView: ImageView? = null

    private var currentPageNumber = -1
    private var chapterId: Int = -1
    private var isLoadingData = false
    private var isLastPageOfData = false
    private var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicListBinding.inflate(layoutInflater)
        if (binding == null) {
            finish()
            return
        }
        setContentView(binding?.root)
        handleIntent(intent)
        setUpNavigationToolbar()
        setUpViews()
        setUpLiveDataObservers()
        currentPageNumber = 0
        isLoadingData = false
    }

    private fun setUpNavigationToolbar() {
        val toolbar: Toolbar? = binding?.toolbar
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(true)
            }
        }
    }

    private fun setUpViews() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = binding?.chapterListRecyclerView
        recyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = chapterListAdapter
        }
        setUpRecyclerViewScrolling()
    }

    private fun setUpLiveDataObservers() {
        chapterListViewModel?.apply {
            this.chapterListLiveData.observe(this@TopicListActivity) {
                parseServerResponse(it)
            }
        }
    }

    private fun setUpRecyclerViewScrolling() {
        val paginationScrollListener = object : PaginationScrollListener(linearLayoutManager) {

            override fun loadMoreItems() {
                //chapterListViewModel?.getChapterListFromServer(chapterId, pageNumber = currentPageNumber)
                // showContentLoadingPgBar(true)
            }

            override fun isLastPage(): Boolean {
                return isLastPageOfData
            }

            override fun isLoading(): Boolean {
                return isLoadingData
            }
        }
        recyclerView?.addOnScrollListener(paginationScrollListener)
    }

    private fun parseServerResponse(serverResponse: ResultWrapper<Any>) {
        when (serverResponse) {
            is ResultWrapper.GenericError -> {
                println("error")
            }
            is ResultWrapper.Success -> {
                val successResponse = serverResponse.value
                if (successResponse is ChapterListResponse) {
                    submitListToRecyclerView(successResponse)
                    val serverTotalPage = successResponse.data?.metadataForPagination?.totalPage
                    isLastPageOfData = if (serverTotalPage != null) {
                        serverTotalPage == (currentPageNumber + 1)
                    } else {
                        true
                    }
                    currentPageNumber++
                    isLoadingData = false
                }
            }
            else -> {

            }
        }
    }

    private fun submitListToRecyclerView(serverResponse: ChapterListResponse?) {
        if (serverResponse == null && serverResponse?.data == null) {
            return
        }
        if (serverResponse.data?.chapters?.isNotEmpty() == true) {
            val chapterList = serverResponse.data?.chapters
            if (chapterList != null) {
                chapterListAdapter.submitList(chapterList)
                showContentLoadingPgBar(false)
            }
        }
    }

    override fun handleResponse(id: Int) {
        this.chapterId = id
        if (currentPageNumber == -1) {
            currentPageNumber = 0
        }
        chapterListViewModel?.getChapterListFromServer(id, pageNumber = currentPageNumber)
        showContentLoadingPgBar(true)
        isLoadingData = true
    }

    private fun showContentLoadingPgBar(canShow: Boolean) {
        if (canShow) {
            binding?.contentLoadingProgressBar?.setViewVisible()
        } else {
            binding?.contentLoadingProgressBar?.setViewGone()
        }
    }
}