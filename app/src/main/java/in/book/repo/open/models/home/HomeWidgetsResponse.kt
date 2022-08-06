package `in`.book.repo.open.models.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

data class HomeWidgetsResponse(
    @SerializedName("data")
    var homeWidget: ArrayList<HomeWidget>? = ArrayList(),
    @SerializedName("errors")
    var errors: ArrayList<Throwable>? = ArrayList(), // Aman
    @SerializedName("message")
    var message: String, // Book list !!
    @SerializedName("statusCode")
    var statusCode: Int, // 200
    @SerializedName("timestamp")
    var timestamp: String // 2022-07-15 02:09:00
)

data class HomeWidget(
    var title: String,
    @SerializedName("books")
    var booksData: ArrayList<Book>? = ArrayList()
)

data class Book(
    var bookId: Int? = -1, // 2
    var colorCode: String? = "", // 123
    var coverImageUrl: String? = "", // SOME
    var id: String? = "", // 62d05fd644f0ad6309676896
    var language: String? = "", // ENGLISH
    var subTitle: String? = "", // GITA-----
    var title: String? = "" // GITA_ENGLISH
)

