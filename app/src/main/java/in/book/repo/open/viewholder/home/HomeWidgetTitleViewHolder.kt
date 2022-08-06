package `in`.book.repo.open.viewholder.home

import `in`.book.repo.open.databinding.HomeWidgetTitleViewholderBinding
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeWidgetTitleViewHolder(binding: HomeWidgetTitleViewholderBinding, widgetsInteraction: WidgetsInteraction) : RecyclerView.ViewHolder(binding.root) {
    private val titleText: TextView by lazy { binding.homeWidgetTitleTV }
    fun bindData(data: Any?) {
        if (data is String) {
            titleText.text = data
        }
    }

}