package com.BookApp.data.repository;

import com.BookApp.data.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    List<Book>findBooksByTitleContaining(String name);
}
