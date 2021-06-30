package com.BookApp.services;

import com.BookApp.data.models.Book;
import com.BookApp.data.repository.BookRepository;
import com.BookApp.web.exceptions.BookNameCannotBeEmptyException;
import com.BookApp.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookServices {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Book> findAllBooks() throws BookNameCannotBeEmptyException {
        return findByName("a");
    }

    @Override
    public List<Book> findByName(String name) throws BookNameCannotBeEmptyException {
      log.info("The name of the book -->{}",name);
        if(name.isEmpty()){
            throw new BookNameCannotBeEmptyException("Book name cannot be empty");
        }
        name = name.replaceAll(" ", "");
        final String URI = "https://www.googleapis.com/books/v1/volumes?q=" + name;
        ResponseEntity<ApiResponse> apiResponseResponseEntity = restTemplate.getForEntity(URI, ApiResponse.class);
        ApiResponse apiResponse = apiResponseResponseEntity.getBody();
        Book book = new Book();
        assert apiResponse != null;
        return apiResponse.getItems().stream().map(
                searchResults -> {
                    VolumeInfo volumeInfo = searchResults.getVolumeInfo();
                    String title = volumeInfo.getTitle();
                    String smallImage = volumeInfo.getImageLinks().toString();
                    book.setCoverImage(smallImage);
                    book.setTitle(title);
                    book.setAuthor(volumeInfo.getAuthors().toString());
                    return book;
                }
        ).collect(Collectors.toList());


    }
}
