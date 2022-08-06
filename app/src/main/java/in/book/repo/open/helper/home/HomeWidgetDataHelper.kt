package `in`.book.repo.open.helper.home

import `in`.book.repo.open.constants.DATA_VT
import `in`.book.repo.open.constants.TITLE_VT
import `in`.book.repo.open.factory.home.models.WidgetsMetaData
import `in`.book.repo.open.models.home.HomeWidget
import `in`.book.repo.open.models.home.HomeWidgetsResponse

interface HomeWidgetDataHelper {

    open suspend fun formatApiResponseOfHomeMetaData(widgetsResponse: HomeWidgetsResponse?) {
        if (widgetsResponse == null) {
            receivedFormattedResponse(ArrayList())
        }
        val widgetDataList = ArrayList<WidgetsMetaData>()
        widgetsResponse?.homeWidget?.forEachIndexed { index: Int, homeWidget: HomeWidget ->
            widgetDataList.add(WidgetsMetaData(TITLE_VT, homeWidget.title))
            widgetDataList.add(WidgetsMetaData(DATA_VT, homeWidget.booksData))
        }
        receivedFormattedResponse(widgetDataList)
    }

    suspend fun receivedFormattedResponse(list: ArrayList<WidgetsMetaData>)

}