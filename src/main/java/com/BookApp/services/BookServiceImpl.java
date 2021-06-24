package com.BookApp.services;

import com.BookApp.data.models.Book;
import com.BookApp.data.repository.BookRepository;
import com.BookApp.web.exceptions.BookNameCannotBeEmptyException;
import com.BookApp.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class BookServiceImpl implements BookServices {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByName(String name) throws BookNameCannotBeEmptyException, BookNotFoundException {
      log.info("The name of the book -->{}",name);
        if(name.isEmpty()){
            throw new BookNameCannotBeEmptyException("Book name cannot be empty");
        }
        List<Book> bookList= bookRepository.findBooksByTitleContaining(name);
        log.info("The list of books -->{}",bookList);
        if(bookList.isEmpty()){
            throw new BookNotFoundException("The Book was not found");
        }
        return bookList;
    }
}
