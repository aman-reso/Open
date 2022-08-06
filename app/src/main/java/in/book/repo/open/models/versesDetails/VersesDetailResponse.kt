package `in`.book.repo.open.models.versesDetails


import com.google.gson.annotations.SerializedName

data class VersesDetailResponse(
    var `data`: Data?=null,
    var errors: ArrayList<Any>,
    var message: String, // Verse details
    var statusCode: Int, // 200
    var timestamp: String // 2022-07-17 11:41:04
) {
    data class Data(
        var bookId: Int, // 1
        var chapterIndex: Int, // 1
        var verseNumber: String,
        @SerializedName("originalVerse_En")
        var originalVerseEn: String?=null, // null
        @SerializedName("originalVerse_Sn")
        var originalVerseValue: String,
        @SerializedName("verseShort")
        var verseTranslation: String,
        @SerializedName("commentary")
        var verseCommentary: String,
    )
}