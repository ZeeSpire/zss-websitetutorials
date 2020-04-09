package com.eskavision.hexagonalarchitecture.database.jparepository;

import com.eskavision.hexagonalarchitecture.database.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HAAJpaRepository extends JpaRepository<BookEntity, Long> {
}
