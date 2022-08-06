package `in`.book.repo.open.viewmodel.home

import `in`.book.repo.open.models.home.HomeWidgetsResponse
import `in`.book.repo.open.network.ResultWrapper
import `in`.book.repo.open.network.getAllHomeWidget
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@ViewModelScoped
class HomeActivityViewModel constructor() : ViewModel() {
    internal var homeWidgetLiveData: MutableLiveData<ResultWrapper<Any>> = MutableLiveData()

    internal fun getHomeWidgetResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            val serverResponse = getAllHomeWidget()
            homeWidgetLiveData.postValue(serverResponse)
        }
    }
}