package `in`.book.repo.open.viewholder.verseDetails

import `in`.book.repo.open.databinding.VerseDetailOriginalSanskritBinding
import androidx.recyclerview.widget.RecyclerView

class VerseInSanskritViewHolder(var binding: VerseDetailOriginalSanskritBinding) :RecyclerView.ViewHolder(binding.root) {
    fun bindData(dataItem: Any?) {
        if (dataItem == null) {
            return
        }
        if (dataItem is String) {
            binding.verseOriginalInSanskrit.text = dataItem
        }
    }
}