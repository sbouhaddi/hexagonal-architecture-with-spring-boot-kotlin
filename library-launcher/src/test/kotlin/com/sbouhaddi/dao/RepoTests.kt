package com.sbouhaddi.dao

import com.sbouhaddi.model.Book
import com.sbouhaddi.repo.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepoTests (
    @Autowired
    val bookRepository: BookRepository
) {

    @Test
    fun `When findByIdOrNull then return Book`() {
        val found = bookRepository.findByIdOrNull(1)
        assertThat(found).isNotNull
    }

    @Test
    fun `When findBooks then return All Books`() {
        val found = bookRepository.findAll()
        assertThat(found).isNotEmpty
        assertThat(found.size).isEqualTo(5)
    }

    @Test
    fun `When createBook then return All Books`() {
        bookRepository.deleteAll()

        val newBook = Book();
        newBook.id = 10
        newBook.title= "title"
        newBook.description = "description"
        newBook.price = Double.NaN
        bookRepository.save(newBook)

        val found = bookRepository.findAll()
        assertThat(found).isNotEmpty
        assertThat(found.size).isEqualTo(1)
    }
}