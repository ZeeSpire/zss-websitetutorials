package com.eskavision.hexagonalarchitecture.core.driver_ports;

import com.eskavision.hexagonalarchitecture.core.domain.Book;
import com.eskavision.hexagonalarchitecture.core.driven_ports.BookRepository;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id);
    }
}
