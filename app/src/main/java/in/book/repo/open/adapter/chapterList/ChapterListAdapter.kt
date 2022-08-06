package `in`.book.repo.open.adapter.chapterList

import `in`.book.repo.open.databinding.ChapterListItemLayoutBinding
import `in`.book.repo.open.models.chapterList.ChapterListResponse
import `in`.book.repo.open.ui.topic_list.INTENT_KEY_BOOK_ID
import `in`.book.repo.open.ui.topic_list.INTENT_KEY_CHAPTER_INDEX
import `in`.book.repo.open.ui.versesList.VersesListActivity
import `in`.book.repo.open.utility.home.DiffCalculator
import `in`.book.repo.open.viewholder.chapterList.ChapterListViewHolder
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChapterListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffCalculator: DiffCalculator by lazy { DiffCalculator() }

    //var asyncListDiffer = AsyncListDiffer(this, diffCalculator.chapterListDiffCallback)
    private var chapterList = ArrayList<ChapterListResponse.Data.Chapter>()
    private fun submitData(list: ArrayList<ChapterListResponse.Data.Chapter>) {
        System.out.println("listCount-->${chapterList.size}")
        if (chapterList.isEmpty()) {
            chapterList.addAll(list)
            notifyItemRangeInserted(0, chapterList.size - 1)
        } else {
            for (e in list) {
                chapterList.add(e)
            }
            val prevListSize = chapterList.size - 1
            val currentListSize = list.size - 1
            notifyItemRangeInserted(currentListSize, prevListSize + currentListSize)
            return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                val binding: ChapterListItemLayoutBinding = ChapterListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ChapterListViewHolder(binding)
            }
            else -> {
                val binding: ChapterListItemLayoutBinding = ChapterListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ChapterListViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = asyncListDiffer.currentList[position]
        val item = chapterList[position]
        if (holder is ChapterListViewHolder) {
            holder.bindData(item)
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, VersesListActivity::class.java)
                intent.putExtra(INTENT_KEY_BOOK_ID, item.bookId)
                intent.putExtra(INTENT_KEY_CHAPTER_INDEX, item.chapterIndex)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getItemCount(): Int {
        println("size-->${chapterList.size}")
        return chapterList.size
//        return asyncListDiffer.currentList.size
    }

    fun submitList(chapterList: ArrayList<ChapterListResponse.Data.Chapter>) {
//         asyncListDiffer.submitList(chapterList.map {
//             it.copy()
//         })
        submitData(chapterList)
    }
}