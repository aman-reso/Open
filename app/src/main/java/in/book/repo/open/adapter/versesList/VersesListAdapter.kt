package `in`.book.repo.open.adapter.versesList

import `in`.book.repo.open.databinding.VersesItemLayoutBinding
import `in`.book.repo.open.models.versesList.VersesListResponse
import `in`.book.repo.open.viewholder.versesList.VersesViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VersesListAdapter(var callback:(VersesListResponse.Data.SubChapter)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<VersesListResponse.Data.SubChapter>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = VersesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VersesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is VersesViewHolder -> {
                holder.bindData(item)
                holder.itemView.setOnClickListener {
                    callback.invoke(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(subChapters: ArrayList<VersesListResponse.Data.SubChapter>) {
        list.addAll(subChapters)
        notifyDataSetChanged()
    }
}