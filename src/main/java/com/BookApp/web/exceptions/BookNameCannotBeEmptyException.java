package com.BookApp.web.exceptions;

public class BookNameCannotBeEmptyException extends Exception {
    public BookNameCannotBeEmptyException() {
    }

    public BookNameCannotBeEmptyException(String message) {
        super(message);
    }

    public BookNameCannotBeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNameCannotBeEmptyException(Throwable cause) {
        super(cause);
    }
}
