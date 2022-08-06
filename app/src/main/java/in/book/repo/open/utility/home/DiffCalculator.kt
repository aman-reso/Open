package `in`.book.repo.open.utility.home

import `in`.book.repo.open.factory.home.models.WidgetsMetaData
import `in`.book.repo.open.models.chapterList.ChapterListResponse
import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffCalculator {
    val homeWidgetsDiffCallback: DiffUtil.ItemCallback<WidgetsMetaData> = object : DiffUtil.ItemCallback<WidgetsMetaData>() {
        override fun areItemsTheSame(oldItem: WidgetsMetaData, newItem: WidgetsMetaData): Boolean {
            return oldItem.data == newItem.data
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: WidgetsMetaData, newItem: WidgetsMetaData): Boolean {
            return oldItem.data === newItem.data
        }
    }
    val chapterListDiffCallback =object :DiffUtil.ItemCallback<ChapterListResponse.Data.Chapter>(){
        override fun areItemsTheSame(oldItem: ChapterListResponse.Data.Chapter, newItem: ChapterListResponse.Data.Chapter): Boolean {
            return oldItem.bookId==newItem.bookId
        }

        override fun areContentsTheSame(oldItem: ChapterListResponse.Data.Chapter, newItem: ChapterListResponse.Data.Chapter): Boolean {
            return oldItem == newItem
        }

    }
}