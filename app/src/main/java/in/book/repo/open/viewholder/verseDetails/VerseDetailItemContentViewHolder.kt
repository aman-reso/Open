package `in`.book.repo.open.viewholder.verseDetails

import `in`.book.repo.open.databinding.VerseDetailItemContentBinding
import androidx.recyclerview.widget.RecyclerView

class VerseDetailItemContentViewHolder(var binding: VerseDetailItemContentBinding) : RecyclerView.ViewHolder(binding.verseDetailItemContent) {
    fun bindData(dataItem: Any?) {
        if (dataItem == null) {
            return
        }
        if (dataItem is String) {
            binding.verseDetailItemContent.text = dataItem
        }
    }
}