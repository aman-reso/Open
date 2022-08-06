package `in`.book.repo.open.viewholder.home

import `in`.book.repo.open.databinding.HomeWidgetContentItemBinding
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import `in`.book.repo.open.models.home.Book
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HomeWidgetMainDataViewHolder(var binding: HomeWidgetContentItemBinding, var interaction: WidgetsInteraction) : RecyclerView.ViewHolder(binding.root) {
    private val titleTextView: TextView by lazy { binding.homeWidgetContentTitleTV }
    private val imageView: ImageView by lazy { binding.homeWidgetContentImageView }
    private val subTitleText: TextView by lazy { binding.homeWidgetContentSubTitleTV }

    fun bindData(s: Any) {
        if (s is Book) {
            titleTextView.text = s.title
            subTitleText.text = s.subTitle
            setUpListeners(s)
            loadImageUrl(s.coverImageUrl)
        }
    }

    private fun setUpListeners(s: Book) {
        binding.root.setOnClickListener {
            interaction.didTapOnBookCard(s)
        }
    }

    private fun loadImageUrl(imgUrl: String?) {
        if (imgUrl != null) {
            Picasso.get().load(imgUrl).into(imageView)
        }
    }

}