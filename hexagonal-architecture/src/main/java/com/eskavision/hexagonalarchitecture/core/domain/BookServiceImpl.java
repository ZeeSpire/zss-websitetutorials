package com.eskavision.hexagonalarchitecture.core.domain;

import com.eskavision.hexagonalarchitecture.core.port.BookRepositoryPort;

public class BookServiceImpl implements BookService {

    private BookRepositoryPort bookRepositoryPort;

    public BookServiceImpl(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    public Book getBook(Long id) {
        return bookRepositoryPort.findById(id);
    }
}
