package `in`.book.repo.open.network

import `in`.book.repo.open.models.home.HomeWidgetsResponse
import io.ktor.client.request.*

suspend inline fun  universalGetRequest(endPoint: String) {
    return ktorHttpClient.get(endPoint)
}

