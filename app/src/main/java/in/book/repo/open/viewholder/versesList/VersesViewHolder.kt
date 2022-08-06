package `in`.book.repo.open.viewholder.versesList

import `in`.book.repo.open.databinding.VersesItemLayoutBinding
import `in`.book.repo.open.models.versesList.VersesListResponse
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VersesViewHolder(binding: VersesItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private val versesTitleTextView: TextView by lazy { binding.verseTitle }
    private val verseNumberTextView: TextView by lazy { binding.verseNumber }
    internal fun bindData(item: VersesListResponse.Data.SubChapter) {
        versesTitleTextView.text = item.description
        if (item.verseNumber.isNullOrEmpty()) {
            return
        } else {
            val verseNum = item.verseNumber
            val newVerse = verseNum?.replace("-", "\n")
            verseNumberTextView.text = newVerse
        }
    }
}