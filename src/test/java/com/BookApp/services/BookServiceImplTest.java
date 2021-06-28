package com.BookApp.services;

import com.BookApp.data.models.Book;
import com.BookApp.web.exceptions.BookNameCannotBeEmptyException;
import com.BookApp.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class BookServiceImplTest {
    @Autowired
    BookServices bookServices;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testThatWeCanFindAllBooks(){
        List<Book> bookList=bookServices.findAllBooks();
        log.info("The books returned are --->{}",bookList);
        assertThat(bookList).hasSize(3);

    }
    @Test
    void testThatWeCanFindBookByTitleContaining() throws BookNotFoundException, BookNameCannotBeEmptyException {
        List<Book> bookList=bookServices.findByName("i");
        log.info("The books returned are --->{}",bookList);
        assertThat(bookList).hasSize(3);
    }
    @Test
    void testThatBookWithoutNameThrowsException(){
        assertThrows(BookNameCannotBeEmptyException.class, ()-> bookServices.findByName(""));

    }
    @Test
    void testThatBookNotFoundThrowsException(){
        assertThrows(BookNotFoundException.class, ()-> bookServices.findByName("##"));

    }
}