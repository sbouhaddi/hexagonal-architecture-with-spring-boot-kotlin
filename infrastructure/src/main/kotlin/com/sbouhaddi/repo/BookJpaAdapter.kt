package com.sbouhaddi.repo

import com.sbouhaddi.data.BookDTO
import com.sbouhaddi.mapper.BookMapper
import com.sbouhaddi.ports.spi.BookPersistencePort
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookJpaAdapter() : BookPersistencePort {

    @Autowired
    lateinit var bookRepository: BookRepository

    private var converter = Mappers.getMapper(BookMapper::class.java)
    override fun addBook(bookDTO: BookDTO): BookDTO {
        val book = converter.bookDtoToBook(bookDTO)
        val savedBook = bookRepository.save(book)
        return converter.bookToBookDto(savedBook)
    }

    override fun deleteBookById(id: Long) {
        if (bookRepository.existsById(id)) bookRepository.deleteById(id)
        else throw NotFoundException()
    }

    override fun updateBook(bookDTO: BookDTO): BookDTO = addBook(bookDTO)

    override fun getBooks(): List<BookDTO> = converter.bookListToBookDtoList(bookRepository.findAll())


    override fun getBookById(id: Long): BookDTO {
        return bookRepository.findById(id).map {
            converter.bookToBookDto(it)
        }.orElse(null) ?: throw NotFoundException()
    }
}