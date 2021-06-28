package com.BookApp.web.controllers;

import com.BookApp.data.models.Book;
import com.BookApp.services.BookServices;
import com.BookApp.web.exceptions.BookNameCannotBeEmptyException;
import com.BookApp.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/bookSearch")
public class BookController {
    @Autowired
    BookServices bookServices;
    @GetMapping("")
    private ResponseEntity<?>bookList(){
        List<Book>books=  bookServices.findAllBooks();
//                .stream()
//                .map(book -> {
//                    try {
//                          return book.add(linkTo(methodOn(BookController.class).findByName(book.getTitle())).withSelfRel(),
//                                linkTo(methodOn(BookController.class).findAll()).withSelfRel());
//                    } catch (BookNotFoundException | BookNameCannotBeEmptyException e) {
//                        e.printStackTrace();
//                        return null;
//                    }
//
//                }).collect(Collectors.toList());
        log.info("The list of books returned are-->{}",books);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> findBooksByName(@PathVariable("id") String bookName) throws BookNotFoundException, BookNameCannotBeEmptyException {
        log.info("The searchKeyword is -->{}",bookName);
        List<Book> bookList = bookServices.findByName(bookName);
//        EntityModel<Book>bookEntityModel=EntityModel.of(
//                bookList,linkTo(methodOn(BookController.class)
//                        .findBooksByName(bookName)).
//                        withSelfRel(),linkTo(methodOn(BookController.class).
//                        findAll()).withRel("books"));
        log.info("The list of books or book related to the keyword -->{} are -->{}",bookName,bookList);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
