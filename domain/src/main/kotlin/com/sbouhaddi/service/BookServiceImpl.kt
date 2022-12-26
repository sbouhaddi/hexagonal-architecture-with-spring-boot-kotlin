package com.sbouhaddi.service

import com.sbouhaddi.data.BookDTO
import com.sbouhaddi.ports.api.BookServicePort
import com.sbouhaddi.ports.spi.BookPersistencePort

class BookServiceImpl(private val bookPersistencePort: BookPersistencePort) : BookServicePort {
    override fun addBook(bookDTO: BookDTO): BookDTO {
        return bookPersistencePort.addBook(bookDTO)
    }

    override fun deleteBookById(id: Long) {
        bookPersistencePort.deleteBookById(id)
    }

    override fun updateBook(bookDTO: BookDTO): BookDTO {
        return bookPersistencePort.updateBook(bookDTO)
    }

    override fun getBooks(): List<BookDTO> {
        return bookPersistencePort.getBooks()
    }

    override fun getBookById(id: Long): BookDTO {
        return bookPersistencePort.getBookById(id)
    }
}