package `in`.book.repo.open.models.versesList


import com.google.gson.annotations.SerializedName

data class VersesListResponse(
    var `data`: Data? = null,
    var errors: ArrayList<Any>? = ArrayList(),
    var message: String? = null, // book subchapter!!
    var statusCode: Int? = -1, // 200
    var timestamp: String? = null // 2022-07-17 10:30:30
) {
    data class Data(
        var bookChapterDTO: BookChapterDTO? = null,
        var metadataForPagination: MetadataForPagination? = null,
        var subChapters: ArrayList<SubChapter>? = null
    ) {
        data class BookChapterDTO(
            var bookId: Int? = -1, // 2
            var chapterHeading: String? = null,
            var chapterIndex: Int? = -1, // 1
            var chapterName: String? = null,
            var description: String? = null,
            @SerializedName("imageUrl")
            var backgroundImageUrl:String?=null
        )

        data class MetadataForPagination(
            var page: Int? = 0, // 0
            var pageSize: Int? = 10, // 10
            var totalPage: Int? = -1, // 4
            var totalRecords: Int? = -1 // 35
        )

        data class SubChapter(
            var description: String? = null, // Dhritarashtra said: O Sanjay, after gathering on the holy field of Kurukshetra, and desiring to fight, what did my sons and the sons of Pandu do?
            var verseNumber: String? = null // 1
        )
    }
}