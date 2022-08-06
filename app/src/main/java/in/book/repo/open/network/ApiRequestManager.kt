package `in`.book.repo.open.network

import `in`.book.repo.open.models.chapterList.ChapterListResponse
import `in`.book.repo.open.models.home.HomeWidgetsResponse
import `in`.book.repo.open.models.versesDetails.VersesDetailResponse
import `in`.book.repo.open.models.versesList.VersesListResponse
import io.ktor.client.request.*

const val DEBUG_BASE_URL = "https://gitamongodb.herokuapp.com"

//list all the server api call function
suspend inline fun getAllHomeWidget() = universalApiRequestManager {
    ktorHttpClient.get<HomeWidgetsResponse>("$DEBUG_BASE_URL/api/books")
}

suspend inline fun getChapterList(id: Int, pageNumber: Int? = 0, pageSize: Int? = 10) = universalApiRequestManager {
    ktorHttpClient.get<ChapterListResponse> {
        url("$DEBUG_BASE_URL/api/books/chapter/$id")
        parameter(CONST_KEY_PAGE_NUMBER, pageNumber)
        parameter(CONST_KEY_PAGE_SIZE, pageSize)
    }
}

suspend fun getVersesList(bookId: Int, chapterIndex: Int) = universalApiRequestManager {
    ktorHttpClient.get<VersesListResponse>("https://gitamongodb.herokuapp.com/api/books/subChapter?bookId=$bookId&chapterIndex=$chapterIndex&pageNumber=0&pageSize=10")
}

suspend fun getVersesDetailsFromServer(bookId: Int? = 2, chapterIndex: Int? = 1, verseNumber: String? = "1") = universalApiRequestManager {
    ktorHttpClient.get<VersesDetailResponse>("https://gitamongodb.herokuapp.com/api/books/verse?bookId=$bookId&chapterIndex=$chapterIndex&verseNumber=$verseNumber")
}

