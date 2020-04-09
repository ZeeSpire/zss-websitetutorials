package com.eskavision.hexagonalarchitecture.db_driven_adapter.domain;

import com.eskavision.hexagonalarchitecture.core.domain.Book;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public BookEntity() {
    }

    public BookEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book toBook(){
        return new Book(this.id, this.name);
    }
}

