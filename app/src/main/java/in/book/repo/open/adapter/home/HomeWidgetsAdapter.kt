package `in`.book.repo.open.adapter.home

import `in`.book.repo.open.constants.DATA_VT
import `in`.book.repo.open.constants.TITLE_VT
import `in`.book.repo.open.databinding.HomeWidgetMainDataLayoutBinding
import `in`.book.repo.open.databinding.HomeWidgetTitleViewholderBinding
import `in`.book.repo.open.factory.home.models.WidgetsMetaData
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import `in`.book.repo.open.utility.home.DiffCalculator
import `in`.book.repo.open.viewholder.home.HomeWidgetContentViewHolder
import `in`.book.repo.open.viewholder.home.HomeWidgetTitleViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

class HomeWidgetsAdapter(var widgetsInteraction: WidgetsInteraction) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val utility by lazy { DiffCalculator() }

    private var asyncListDiff = AsyncListDiffer(this, utility.homeWidgetsDiffCallback)

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_VT -> {
                val binding: HomeWidgetTitleViewholderBinding = HomeWidgetTitleViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeWidgetTitleViewHolder(binding,widgetsInteraction)
            }
            DATA_VT -> {
                val binding: HomeWidgetMainDataLayoutBinding = HomeWidgetMainDataLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeWidgetContentViewHolder(binding,widgetsInteraction)
            }
            else -> {
                val binding: HomeWidgetTitleViewholderBinding = HomeWidgetTitleViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeWidgetTitleViewHolder(binding,widgetsInteraction)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeWidgetContentViewHolder -> {
                holder.submitData(asyncListDiff.currentList[position].data )
            }
            is HomeWidgetTitleViewHolder -> {
                holder.bindData(asyncListDiff.currentList[position].data)
            }
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiff.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return asyncListDiff.currentList[position].viewType
    }

    fun submitList(list: ArrayList<WidgetsMetaData>) {
        asyncListDiff.submitList(list.map {
            it.copy()
        })
    }
}

fun getDummy(): ArrayList<String> {
    val list = ArrayList<String>()
    list.add("Aman")
    list.add("Aman")
    list.add("Aman")
    list.add("Aman")
    list.add("Aman")
    list.add("Aman")
    return list
}