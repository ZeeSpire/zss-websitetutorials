package com.eskavision.hexagonalarchitecture.core.port;

import com.eskavision.hexagonalarchitecture.core.domain.Book;

public interface BookRepositoryPort {
    Book findById(Long id);
}
