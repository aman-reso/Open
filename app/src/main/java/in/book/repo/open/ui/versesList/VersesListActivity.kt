package `in`.book.repo.open.ui.versesList

import `in`.book.repo.open.R
import `in`.book.repo.open.adapter.verse_details.ReadVerseDetailsAdapter
import `in`.book.repo.open.adapter.versesList.VersesListAdapter
import `in`.book.repo.open.databinding.ActivityVersesListBinding
import `in`.book.repo.open.databinding.BtmSheetVersesDetailBinding
import `in`.book.repo.open.mapper.VerseDetailResponseToVerseDetailItemMapper
import `in`.book.repo.open.models.versesDetails.VerseDetailItem
import `in`.book.repo.open.models.versesDetails.VersesDetailResponse
import `in`.book.repo.open.models.versesList.VersesListResponse
import `in`.book.repo.open.network.ResultWrapper
import `in`.book.repo.open.network.getVersesDetailsFromServer
import `in`.book.repo.open.network.getVersesList
import `in`.book.repo.open.setViewGone
import `in`.book.repo.open.setViewVisible
import `in`.book.repo.open.ui.topic_list.interfaces.VersesListIntentHandler
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VersesListActivity : AppCompatActivity(), VersesListIntentHandler, VerseDetailResponseToVerseDetailItemMapper {
    private val versesListAdapter: VersesListAdapter by lazy {
        VersesListAdapter(callbackWhenUserTapOnItem)
    }
    private val verseDetailAdapter: ReadVerseDetailsAdapter by lazy { ReadVerseDetailsAdapter() }
    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null
    private val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    //binding and view
    private var binding: ActivityVersesListBinding? = null
    private var verseReadBtmSheetBinding: BtmSheetVersesDetailBinding? = null
    private var verseListRecyclerView: RecyclerView? = null
    private var verseDetailRecyclerView: RecyclerView? = null

    //variables
    private var bookId: Int = -1
    private var chapterIndex: Int = -1
    private val verseStringBuilder: StringBuilder by lazy { StringBuilder() }
    private var bookChapterDto: VersesListResponse.Data.BookChapterDTO? = null

    //lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVersesListBinding.inflate(layoutInflater)
        if (binding == null) {
            finish()
        }
        setContentView(binding!!.root)
        handleIntent(intent)
        setUpNavigationToolbar()
        setRecyclerView(binding!!)
        setUpBtmSheet(binding!!)
    }

    private fun setUpNavigationToolbar() {
        val toolbar: Toolbar? = binding?.verseListToolbar
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(true)
            }
        }
    }

    private fun setRecyclerView(binding: ActivityVersesListBinding) {
        verseListRecyclerView = binding.versesListRecyclerView
        verseListRecyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = versesListAdapter
        }
    }

    private fun makeApiCall(bookId: Int, chapterIndex: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            when (val serverResponse = getVersesList(bookId, chapterIndex)) {
                is ResultWrapper.Success -> {
                    val successResponse = serverResponse.value
                    if (successResponse.data?.subChapters != null) {
                        if (bookChapterDto == null) {
                            bookChapterDto = successResponse.data?.bookChapterDTO
                        }
                        submitDataToAdapter(successResponse.data?.subChapters)
                    }
                }
            }
        }
    }

    private fun submitDataToAdapter(subChapters: ArrayList<VersesListResponse.Data.SubChapter>?) {
        subChapters?.let {
            lifecycleScope.launch(Dispatchers.Main) {
                versesListAdapter.submitList(subChapters)
                showContentLoadingPgBar(false)
            }
        }
    }

    override fun handleResponse(bookId: Int, chapterIndex: Int) {
        if (bookId != -1 && chapterIndex != -1) {
            this.bookId = bookId
            this.chapterIndex = chapterIndex
            makeApiCall(bookId, chapterIndex)
            showContentLoadingPgBar(true)
        }
    }

    private fun setUpBtmSheet(binding: ActivityVersesListBinding) {
        verseReadBtmSheetBinding = binding.readVerseBtmSheetParentId
        if (verseReadBtmSheetBinding?.root != null) {
            verseDetailRecyclerView = verseReadBtmSheetBinding?.btmSheetVerseDetailRecyclerView
            mBottomSheetBehavior = BottomSheetBehavior.from(verseReadBtmSheetBinding!!.root)
            verseReadBtmSheetBinding?.root?.setOnClickListener {
                mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        verseDetailRecyclerView?.apply {
            adapter = verseDetailAdapter
            layoutManager = LinearLayoutManager(this@VersesListActivity)
        }
    }

    private fun makeBtmSheetExpanded() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getVerseDetails(bookId: Int, chapterIndex: Int, verseNumber: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            when (val serverResponse = getVersesDetailsFromServer(bookId, chapterIndex, verseNumber)) {
                is ResultWrapper.Success -> {
                    lifecycleScope.launch(Dispatchers.Main) {
                        bindDataToBtmSheet(serverResponse.value)
                    }
                }
            }
        }
    }

    private fun bindDataToBtmSheet(versesDetailResponse: VersesDetailResponse?) {
        verseStringBuilder.clear()
        val verseFormattedValue = verseStringBuilder.append("Bhagavad Gita: ").append("Chapter ").append(chapterIndex).append(", ").append("Verse ")
        verseDetailToItemMapper(versesDetailResponse, verseFormattedValue)
    }

    private var callbackWhenUserTapOnItem = fun(value: VersesListResponse.Data.SubChapter) {
        if (value.verseNumber != null) {
            getVerseDetails(bookId, chapterIndex, value.verseNumber!!)
            makeBtmSheetExpanded()
        }
    }

    private fun showContentLoadingPgBar(canShow: Boolean) {
        if (canShow) {
            binding?.contentLoadingProgressBar?.setViewVisible()
        } else {
            binding?.contentLoadingProgressBar?.setViewGone()
        }
    }

    override fun getVerseDetailItem(verseDetailItems: ArrayList<VerseDetailItem>) {
        verseDetailAdapter.submitData(verseDetailItems)
    }
}