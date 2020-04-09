package com.eskavision.hexagonalarchitecture.core.driver_ports;

import com.eskavision.hexagonalarchitecture.core.domain.book.Book;

public interface BookService {
    Book getBook(Long id);
}
