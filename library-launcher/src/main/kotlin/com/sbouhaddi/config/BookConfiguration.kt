package com.sbouhaddi.config

import com.sbouhaddi.ports.api.BookServicePort
import com.sbouhaddi.ports.spi.BookPersistencePort
import com.sbouhaddi.repo.BookJpaAdapter
import com.sbouhaddi.service.BookServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class BookConfiguration {
    @Bean
    fun bookPersistence(): BookPersistencePort {
        return BookJpaAdapter()
    }

    @Bean
    fun bookService(): BookServicePort {
        return BookServiceImpl(bookPersistence())
    }
}
