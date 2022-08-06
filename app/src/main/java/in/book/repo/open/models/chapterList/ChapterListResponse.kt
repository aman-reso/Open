package `in`.book.repo.open.models.chapterList


import com.google.gson.annotations.SerializedName

data class ChapterListResponse(
    @SerializedName("data")
    var `data`: Data? = null,
    @SerializedName("errors")
    var errors: ArrayList<Throwable>? = ArrayList(),
    @SerializedName("message")
    var message: String? = null, // book chapters!!
    @SerializedName("statusCode")
    var statusCode: Int? = -1, // 201
    @SerializedName("timestamp")
    var timestamp: String? = "" // 2022-07-17 02:29:13
) {
    data class Data(
        var bookResponse: BookResponse?=null,
        var chapters: ArrayList<Chapter>?= ArrayList(),
        var metadataForPagination: MetadataForPagination?=null
    ) {
        data class BookResponse(
            var bookId: Int?=-1, // 2
            var colorCode: String?="", // 123
            var coverImageUrl: String?="", // 123
            var language: String?="", // ENGLISH
            var subTitle: String?="", // GITA-----
            var title: String?="" // GITA_ENGLISH
        )

        data class Chapter(
            var bookId: Int?=-1, // 2
            var chapterHeading: String?="", //  Lamenting the Consequences of War
            var chapterIndex: Int?=-1, // 1
            var chapterName: String?="", // Arjun Viṣhād Yog
            var description: String?="",
            @SerializedName("imageUrl")
            var imageUrl:String?=null// The Bhagavad Gita, or the song of God, was revealed by Lord Shree Krishna to Arjun on the threshold of the epic war of Mahabharata. A decisive battle between two sets of cousins, the Kauravas and the Pandavas, was just about to commence on the battlefield of Kurukshetra. A detailed account of the reasons that led to such a colossal war; is given under Introduction-The Setting of the Bhagavad Gita. The Bhagavad Gita is primarily a conversation between Lord Shree Krishna and Arjun. However, the first chapter begins with a dialogue between King Dhritarashtra and his minister Sanjay. Dhritarashtra being blind, could not leave his palace in Hastinapur but was eager to know the ongoings of the battlefield. Sanjay was a disciple of Sage Ved Vyas, the author of the epic Mahabharata and several other Hindu scriptures. Sage Ved Vyas possessed a mystic ability to see and hear events occurring in distant places. He had bestowed upon Sanjay the miraculous power of distant vision. Therefore, Sanjay could see and hear, what transpired on the battleground of Kurukshetra, and gave a first-hand account to King Dhritarashtra while still being in his palace.
        )

        data class MetadataForPagination(
            var page: Int?=0, // 0
            var pageSize: Int?=-1, // default 10
            var totalPage: Int?=-1, //
            var totalRecords: Int?=-1 // 18
        )
    }
}