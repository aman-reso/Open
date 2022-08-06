package `in`.book.repo.open.viewholder.home

import `in`.book.repo.open.adapter.home.HomeMainContentItemAdapter
import `in`.book.repo.open.databinding.HomeWidgetMainDataLayoutBinding
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import androidx.recyclerview.widget.RecyclerView

class HomeWidgetContentViewHolder(binding: HomeWidgetMainDataLayoutBinding, var widgetsInteraction: WidgetsInteraction) : RecyclerView.ViewHolder(binding.root) {
    private var recyclerView = binding.homeContentMainRecyclerView
    private var homeMainContentItemAdapter = HomeMainContentItemAdapter(widgetsInteraction)

    init {
        recyclerView.apply {
            adapter = homeMainContentItemAdapter
        }
    }

    public fun submitData(list: Any?) {
        if (list == null) {
            return
        }
        if (list is ArrayList<*>) {
            homeMainContentItemAdapter.submitList(list as ArrayList<Any>)
        }

    }
}