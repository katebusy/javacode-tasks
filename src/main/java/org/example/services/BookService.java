package org.example.services;

import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book getBookById(UUID id) {
        return repository.findById(id);
    }

    public Iterable<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book updateBook(UUID id, Book book) {
        book.setId(id);
        return repository.save(book);
    }

    public void deleteBook(UUID id) {
        repository.deleteById(id);
    }
}
