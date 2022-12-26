package com.sbouhaddi.ports.api

import com.sbouhaddi.data.BookDTO

interface BookServicePort {

    fun addBook(bookDTO: BookDTO): BookDTO
    fun deleteBookById(id: Long)
    fun updateBook(bookDTO: BookDTO): BookDTO
    fun getBooks(): List<BookDTO>
    fun getBookById(id: Long): BookDTO
}