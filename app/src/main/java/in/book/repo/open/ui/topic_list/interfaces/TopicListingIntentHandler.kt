package `in`.book.repo.open.ui.topic_list.interfaces

import `in`.book.repo.open.ui.INTENT_KEY_FOR_TOPIC_ID
import `in`.book.repo.open.ui.topic_list.INTENT_KEY_BOOK_ID
import `in`.book.repo.open.ui.topic_list.INTENT_KEY_CHAPTER_INDEX
import android.content.Intent

interface TopicListingIntentHandler {
    fun handleIntent(intent: Intent?) {
        if (intent == null) {
            handleResponse(-1)
            return
        }
        if (intent.hasExtra(INTENT_KEY_FOR_TOPIC_ID)) {
            val id = intent.getIntExtra(INTENT_KEY_FOR_TOPIC_ID, -1)
            handleResponse(id)
        }
    }

    fun handleResponse(id: Int)
}

interface VersesListIntentHandler {
    fun handleIntent(intent: Intent?) {
        if (intent == null) {
            handleResponse(-1,-1)
            return
        }
        if (intent.hasExtra(INTENT_KEY_BOOK_ID)) {
            val id = intent.getIntExtra(INTENT_KEY_BOOK_ID, -1)
            if (intent.hasExtra(INTENT_KEY_CHAPTER_INDEX)) {
                val chapterIndex = intent.getIntExtra(INTENT_KEY_CHAPTER_INDEX, -1)
                handleResponse(id, chapterIndex)
            }
        }
    }

    fun handleResponse(bookId: Int, chapterIndex: Int)
}