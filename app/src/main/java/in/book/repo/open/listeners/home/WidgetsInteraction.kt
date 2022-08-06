package `in`.book.repo.open.listeners.home

import `in`.book.repo.open.models.home.Book

interface WidgetsInteraction {
    fun didTapOnBookCard(book: Book?)
    fun didTapOnContinueReading()
}