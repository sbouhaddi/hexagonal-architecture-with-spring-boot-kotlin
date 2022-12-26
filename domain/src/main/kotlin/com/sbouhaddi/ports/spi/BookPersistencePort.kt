package com.sbouhaddi.ports.spi

import com.sbouhaddi.data.BookDTO

interface BookPersistencePort {
    fun addBook(bookDTO: BookDTO): BookDTO
    fun deleteBookById(id: Long)
    fun updateBook(bookDTO: BookDTO): BookDTO
    fun getBooks(): List<BookDTO>
    fun getBookById(id: Long): BookDTO
}