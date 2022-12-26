package com.sbouhaddi.mapper

import com.sbouhaddi.data.BookDTO
import com.sbouhaddi.model.Book
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper
interface BookMapper {

    @InheritInverseConfiguration
    fun bookToBookDto(book: Book): BookDTO
    fun bookDtoToBook(bookDto: BookDTO): Book
    fun bookListToBookDtoList(books: List<Book>): List<BookDTO>
    fun bookDtoListToBookList(booksDto: List<BookDTO>): List<Book>
}