package `in`.book.repo.open.ui

import `in`.book.repo.open.adapter.home.HomeWidgetsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.book.repo.open.databinding.HomeFragmentBinding
import `in`.book.repo.open.factory.home.models.WidgetsMetaData
import `in`.book.repo.open.helper.home.HomeWidgetDataHelper
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import `in`.book.repo.open.models.home.Book
import `in`.book.repo.open.models.home.HomeWidgetsResponse
import `in`.book.repo.open.network.ResultWrapper
import `in`.book.repo.open.ui.topic_list.TopicListActivity
import `in`.book.repo.open.viewmodel.home.HomeActivityViewModel
import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
const val INTENT_KEY_FOR_TOPIC_ID="intent_key_topic_id"
@AndroidEntryPoint
class HomeFragment : Fragment(), HomeWidgetDataHelper, WidgetsInteraction {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private var shimmerLoadingView: ShimmerFrameLayout? = null
    private val homeViewModel: HomeActivityViewModel? by viewModels()
    private val homeWidgetsAdapter: HomeWidgetsAdapter by lazy { HomeWidgetsAdapter(this) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.homeRecyclerView
        shimmerLoadingView = binding.loaderShimmerLayoutContainer.loaderShimmerLayoutContainer
        recyclerView?.apply {
            adapter = homeWidgetsAdapter
        }
        setLiveDataObservers()
    }

    private fun setLiveDataObservers() {
        homeViewModel?.getHomeWidgetResponse()
        showHideShimmer(true)
        homeViewModel?.homeWidgetLiveData?.observe(viewLifecycleOwner) {
            showHideShimmer(false)
            parseServerResponse(it)
        }
    }

    private fun parseServerResponse(resultWrapper: ResultWrapper<Any>) {
        when (resultWrapper) {
            is ResultWrapper.GenericError -> {
                println("error")
            }
            is ResultWrapper.Success -> {
                val successResponse = resultWrapper.value
                lifecycleScope.launch(Dispatchers.IO) {
                    if (successResponse is HomeWidgetsResponse) {
                        formatApiResponseOfHomeMetaData(successResponse)
                    }
                }
            }
            else -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override suspend fun receivedFormattedResponse(list: ArrayList<WidgetsMetaData>) {
        lifecycleScope.launch(Dispatchers.Main) {
            homeWidgetsAdapter.submitList(list)
        }
    }

    private fun showHideShimmer(canShow: Boolean) {
        if (canShow) {
            shimmerLoadingView?.visibility = View.VISIBLE
            shimmerLoadingView?.startShimmer()
        } else {
            shimmerLoadingView?.stopShimmer()
            shimmerLoadingView?.visibility = View.GONE
        }
    }

    override fun didTapOnBookCard(book: Book?) {
        book?.let {
            startChapterListActivity(book.bookId)
        }
    }

    override fun didTapOnContinueReading() {

    }

    private fun startChapterListActivity(bookId: Int?) {
        if (context != null && bookId!=null) {
            val chapterListingIntent = Intent(context, TopicListActivity::class.java)
            chapterListingIntent.putExtra(INTENT_KEY_FOR_TOPIC_ID,bookId)
            startActivity(chapterListingIntent)
        }
    }
}