package `in`.book.repo.open.adapter.verse_details

import `in`.book.repo.open.databinding.VerseDetailItemContentBinding
import `in`.book.repo.open.databinding.VerseDetailOriginalSanskritBinding
import `in`.book.repo.open.databinding.VerseDetailsTitleBinding
import `in`.book.repo.open.models.versesDetails.VerseDetailItem
import `in`.book.repo.open.viewholder.verseDetails.VerseDetailItemContentViewHolder
import `in`.book.repo.open.viewholder.verseDetails.VerseDetailTitleViewHolder
import `in`.book.repo.open.viewholder.verseDetails.VerseInSanskritViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReadVerseDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<VerseDetailItem>()

    private fun submitList(list: ArrayList<VerseDetailItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VD_TITLE -> {
                val binding = VerseDetailsTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VerseDetailTitleViewHolder(binding)
            }
            VD_SANSKRIT -> {
                val binding = VerseDetailOriginalSanskritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VerseInSanskritViewHolder(binding)
            }
            VD_CONTENT -> {
                val binding = VerseDetailItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VerseDetailItemContentViewHolder(binding)
            }
            else -> {
                val binding = VerseDetailOriginalSanskritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VerseInSanskritViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = list[position].data
        when (holder) {
            is VerseDetailItemContentViewHolder -> {
                holder.bindData(dataItem)
            }
            is VerseInSanskritViewHolder -> {
                holder.bindData(dataItem)
            }
            is VerseDetailTitleViewHolder -> {
                holder.bindData(dataItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    fun submitData(verseDetailItems: ArrayList<VerseDetailItem>) {
        submitList(verseDetailItems)
    }
}

const val VD_TITLE = 1
const val VD_SANSKRIT = 2
const val VD_CONTENT = 3
