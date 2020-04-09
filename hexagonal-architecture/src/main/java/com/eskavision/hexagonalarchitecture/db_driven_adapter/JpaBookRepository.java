package com.eskavision.hexagonalarchitecture.db_driven_adapter;

import com.eskavision.hexagonalarchitecture.core.domain.Book;
import com.eskavision.hexagonalarchitecture.core.domain.BookDoesNotExistException;
import com.eskavision.hexagonalarchitecture.core.driven_ports.BookRepository;
import com.eskavision.hexagonalarchitecture.db_driven_adapter.domain.BookEntity;
import com.eskavision.hexagonalarchitecture.db_driven_adapter.jparepository.HAAJpaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaBookRepository implements BookRepository {

    @Autowired
    private HAAJpaRepository haaJpaRepository;

    @Override
    public Book findById(Long id) {
        Optional<BookEntity> bookEntityOptional = haaJpaRepository.findById(id);
        BookEntity bookEntity = bookEntityOptional.orElseThrow(BookDoesNotExistException::new);
        return bookEntity.toBook();
    }
}
