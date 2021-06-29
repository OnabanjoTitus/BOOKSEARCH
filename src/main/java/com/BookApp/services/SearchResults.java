package com.BookApp.services;

import com.BookApp.data.models.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {
        private String id;
        private VolumeInfo volumeInfo;

}
