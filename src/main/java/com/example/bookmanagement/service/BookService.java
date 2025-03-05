package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Get all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Get a book by ID
    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    // Add a new book
    public Book addBook(Book book) {
        book.setId(idCounter.getAndIncrement());
        books.add(book);
        return book;
    }

    // // Update a book by ID
    // public Optional<Book> updateBook(Long id, Book updatedBook) {
    //     Optional<Book> existingBook = getBookById(id);
    //     existingBook.ifPresent(book -> {
    //         book.setTitle(updatedBook.getTitle());
    //         book.setAuthor(updatedBook.getAuthor());
    //         book.setPrice(updatedBook.getPrice());
    //     });
    //     return existingBook;
    // }

    // Delete a book by ID
    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}