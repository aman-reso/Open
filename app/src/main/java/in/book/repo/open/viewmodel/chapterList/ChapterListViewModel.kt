package `in`.book.repo.open.viewmodel.chapterList

import `in`.book.repo.open.network.ResultWrapper
import `in`.book.repo.open.network.getChapterList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ViewModelScoped
class ChapterListViewModel : ViewModel() {
    internal var chapterListLiveData: MutableLiveData<ResultWrapper<Any>> = MutableLiveData()

    internal fun getChapterListFromServer(id: Int,pageNumber:Int?=0,pageSize:Int?=10) {
        viewModelScope.launch(Dispatchers.IO) {
            val serverResponse = getChapterList(id,pageNumber, pageSize)
            chapterListLiveData.postValue(serverResponse)
        }
    }
}