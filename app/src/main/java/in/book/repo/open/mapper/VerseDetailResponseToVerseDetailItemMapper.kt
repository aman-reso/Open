package `in`.book.repo.open.mapper

import `in`.book.repo.open.adapter.verse_details.VD_CONTENT
import `in`.book.repo.open.adapter.verse_details.VD_SANSKRIT
import `in`.book.repo.open.adapter.verse_details.VD_TITLE
import `in`.book.repo.open.models.versesDetails.VerseDetailItem
import `in`.book.repo.open.models.versesDetails.VersesDetailResponse

interface VerseDetailResponseToVerseDetailItemMapper {
    fun verseDetailToItemMapper(verseDetail: VersesDetailResponse?, firstTitleText: StringBuilder) {
        val items = ArrayList<VerseDetailItem>()
        items.clear()
        items.add(VerseDetailItem(VD_TITLE, firstTitleText.append(verseDetail?.data?.verseNumber)))
        if (verseDetail?.data != null) {
            items.add(VerseDetailItem(VD_SANSKRIT, verseDetail.data?.originalVerseValue))
            if (!verseDetail.data?.originalVerseEn.isNullOrEmpty()) {
                items.add(VerseDetailItem(VD_TITLE, "Pronunciation"))
                items.add(VerseDetailItem(VD_CONTENT, verseDetail.data?.originalVerseEn))
            }
            if (!verseDetail.data?.verseTranslation.isNullOrEmpty()) {
                items.add(VerseDetailItem(VD_TITLE, "Translation"))
                items.add(VerseDetailItem(VD_CONTENT, verseDetail.data?.verseTranslation))
            }
            if (!verseDetail.data?.verseCommentary.isNullOrEmpty()) {
                items.add(VerseDetailItem(VD_TITLE, "Commentary"))
                items.add(VerseDetailItem(VD_CONTENT, verseDetail.data?.verseCommentary))
            }
        }
        getVerseDetailItem(items)
    }

    fun getVerseDetailItem(verseDetailItems: ArrayList<VerseDetailItem>)
}