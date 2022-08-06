package `in`.book.repo.open.viewholder.chapterList

import `in`.book.repo.open.databinding.ChapterListItemLayoutBinding
import `in`.book.repo.open.models.chapterList.ChapterListResponse
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ChapterListViewHolder(binding: ChapterListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private var titleTextView: TextView = binding.chapterListItemTitle
    private var subTitleTextView: TextView = binding.chapterListSubTitle
    private var resumeReadingIcon: ImageView = binding.chapterListItemPlayBtn
    private var chapterIcon: ImageView = binding.chapterListItemIcon
    private var picasso = Picasso.get()
    internal fun bindData(item: ChapterListResponse.Data.Chapter) {
        titleTextView.text = item.chapterName
        subTitleTextView.text = item.chapterHeading
        if (item.imageUrl.isNullOrEmpty()) {
            return
        } else {
            picasso.isLoggingEnabled = true
            picasso.load(item.imageUrl).resize(50, 50).into(chapterIcon)
        }
    }
}
