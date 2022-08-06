package `in`.book.repo.open.network

import io.ktor.client.features.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.UnknownHostException
//generic function
suspend fun <T> universalApiRequestManager(apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(Dispatchers.IO) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    ResultWrapper.NetworkError
                }
                is UnknownHostException -> {
                    ResultWrapper.GenericError(null, null)
                }
                is ClientRequestException ->{
                    ResultWrapper.GenericError(throwable.response.status.value,null)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}