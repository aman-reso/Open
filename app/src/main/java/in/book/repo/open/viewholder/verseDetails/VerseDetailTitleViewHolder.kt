package `in`.book.repo.open.viewholder.verseDetails

import `in`.book.repo.open.databinding.VerseDetailsTitleBinding
import androidx.recyclerview.widget.RecyclerView

class VerseDetailTitleViewHolder(var binding: VerseDetailsTitleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(dataItem: Any?) {
        if (dataItem == null) {
            return
        }
        if (dataItem is String || dataItem is StringBuilder) {
            binding.verseDetailItemTitleText.text = dataItem.toString()
        }
    }

}