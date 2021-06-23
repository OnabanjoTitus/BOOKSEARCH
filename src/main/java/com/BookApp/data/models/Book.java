package com.BookApp.data.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
public class Book {
    @Id
    private Integer bookId;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String coverImage;
}
