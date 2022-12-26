package com.sbouhaddi.controller

import com.sbouhaddi.data.BookDTO
import com.sbouhaddi.ports.api.BookServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController() {
    @Autowired
    lateinit var bookService: BookServicePort

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBook(@RequestBody bookDTO: BookDTO): BookDTO = bookService.addBook(bookDTO)

    @GetMapping("/get")
    fun getAllBooks(): List<BookDTO> = bookService.getBooks()

    @GetMapping("/{id}")
    fun getBookByIs(@PathVariable id: Long) = bookService.getBookById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Long) = bookService.deleteBookById(id)

    @PutMapping("/update")
    fun updateBook(
        @RequestBody bookDTO: BookDTO
    ) = bookService.updateBook(bookDTO)
}