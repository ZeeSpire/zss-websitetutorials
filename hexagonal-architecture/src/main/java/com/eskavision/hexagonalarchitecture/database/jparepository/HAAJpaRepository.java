package com.eskavision.hexagonalarchitecture.database.jparepository;

import com.eskavision.hexagonalarchitecture.database.domain.BookEntity;

public interface HAAJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<BookEntity, Long> {
}
