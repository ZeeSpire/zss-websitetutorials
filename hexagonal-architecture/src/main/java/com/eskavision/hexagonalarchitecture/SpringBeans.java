package com.eskavision.hexagonalarchitecture;

import com.eskavision.hexagonalarchitecture.core.driven_ports.BookRepository;
import com.eskavision.hexagonalarchitecture.core.driver_ports.BookService;
import com.eskavision.hexagonalarchitecture.core.driver_ports.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeans {
        @Bean
        BookService bookService(final BookRepository bookRepository) {
            return new BookServiceImpl(bookRepository);

    }
}
