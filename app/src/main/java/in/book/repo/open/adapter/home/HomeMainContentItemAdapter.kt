package `in`.book.repo.open.adapter.home

import `in`.book.repo.open.databinding.HomeWidgetContentItemBinding
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import `in`.book.repo.open.viewholder.home.HomeWidgetMainDataViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeMainContentItemAdapter(var widgetsInteraction: WidgetsInteraction) : RecyclerView.Adapter<HomeWidgetMainDataViewHolder>() {
    private var list = ArrayList<Any>()
    fun submitList(list: ArrayList<Any>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWidgetMainDataViewHolder {
        val binding = HomeWidgetContentItemBinding.inflate(LayoutInflater.from(parent.context,),parent,false)
        return HomeWidgetMainDataViewHolder(binding,widgetsInteraction)
    }

    override fun onBindViewHolder(holder: HomeWidgetMainDataViewHolder, position: Int) {
         holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}