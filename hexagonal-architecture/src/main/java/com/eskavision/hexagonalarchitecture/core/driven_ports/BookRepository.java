package com.eskavision.hexagonalarchitecture.core.driven_ports;

import com.eskavision.hexagonalarchitecture.core.domain.book.Book;

public interface BookRepository {
    Book findById(Long id);
}
