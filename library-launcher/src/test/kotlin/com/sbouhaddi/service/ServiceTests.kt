package com.sbouhaddi.service

import com.sbouhaddi.data.BookDTO
import com.sbouhaddi.mapper.BookMapper
import com.sbouhaddi.model.Book
import com.sbouhaddi.ports.spi.BookPersistencePort
import com.sbouhaddi.repo.BookJpaAdapter
import com.sbouhaddi.repo.BookRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mapstruct.factory.Mappers
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.client.HttpClientErrorException.NotFound
import java.util.*

@ExtendWith(MockKExtension::class)
class ServiceTests {


    private var bookRepository: BookRepository = mockk<BookRepository>()

    @InjectMockKs
    var service = BookJpaAdapter()

    @SpyK
    var converter = Mappers.getMapper(BookMapper::class.java)

    @Test
    fun `remove book`() {
        every { bookRepository.existsById(1) } returns true
        justRun { bookRepository.deleteById(1) }
        every { bookRepository.findAll() } returns emptyList()
        service.deleteBookById(1)
        assertThat(service.getBooks()).isEmpty()
    }

    @Test
    fun `update or create book`() {
        val newBook = BookDTO(1, "Clean Code", "A handbook of agile Software", Double.NaN)
        val book = converter.bookDtoToBook(newBook);
        every { bookRepository.save(book) } answers { book }
        val savedBook = service.addBook(newBook)
        verify { bookRepository.save(book) }
        assertThat(savedBook).isNotNull
    }

    @Test
    fun `delete book exception`() {
        val block: () -> Unit = {
            every { bookRepository.existsById(1) } returns false
            service.deleteBookById(1)
        }
        assertThrows<NotFoundException>(block);
    }

    @Test
    fun `get book exception`() {
        val block: () -> Unit = {
            every { bookRepository.findById(1) } answers { Optional.empty()}
            service.getBookById(1)
        }
        assertThrows<NotFoundException>(block);
    }
}