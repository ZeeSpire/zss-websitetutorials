package com.eskavision.hexagonalarchitecture.database;

import com.eskavision.hexagonalarchitecture.core.domain.Book;
import com.eskavision.hexagonalarchitecture.core.domain.BookDoesNotExistException;
import com.eskavision.hexagonalarchitecture.core.port.BookRepositoryPort;
import com.eskavision.hexagonalarchitecture.database.domain.BookEntity;
import com.eskavision.hexagonalarchitecture.database.jparepository.HAAJpaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaBookRepositoryAdapter implements BookRepositoryPort {

    @Autowired
    private HAAJpaRepository haaJpaRepository;

    @Override
    public Book findById(Long id) {
        Optional<BookEntity> bookEntityOptional = haaJpaRepository.findById(id);
        BookEntity bookEntity = bookEntityOptional.orElseThrow(BookDoesNotExistException::new);
        return bookEntity.toBook();
    }
}
