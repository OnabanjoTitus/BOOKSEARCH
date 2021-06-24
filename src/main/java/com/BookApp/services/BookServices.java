package com.BookApp.services;

import com.BookApp.data.models.Book;
import com.BookApp.web.exceptions.BookNameCannotBeEmptyException;
import com.BookApp.web.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookServices {
    List<Book>findAllBooks();
    List<Book>findByName(String name) throws BookNameCannotBeEmptyException, BookNotFoundException;
}
