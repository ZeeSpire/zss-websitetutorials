package com.eskavision.hexagonalarchitecture;

import com.eskavision.hexagonalarchitecture.core.port.BookRepositoryPort;
import com.eskavision.hexagonalarchitecture.core.domain.BookService;
import com.eskavision.hexagonalarchitecture.core.domain.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeans {
        @Bean
        BookService bookService(final BookRepositoryPort bookRepositoryPort) {
            return new BookServiceImpl(bookRepositoryPort);

    }
}
