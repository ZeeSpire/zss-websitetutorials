package com.eskavision.hexagonalarchitecture.db_driven_adapter.jparepository;

import com.eskavision.hexagonalarchitecture.db_driven_adapter.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HAAJpaRepository extends JpaRepository<BookEntity, Long> {
}
