package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Book> getAllBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Book getBookById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no user with this id: %s".formatted(id)));
    }
    @Transactional
    public Book createBook(Book book) {
        return repository.save(book);
    }
    @Transactional
    public Book updateBook(UUID id, Book book) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no book with id: " + id);
        }
        book.setId(id);
        return repository.saveAndFlush(book);
    }
    @Transactional
    public void deleteBook(UUID id) {
        if (repository.existsById(id)) {
            throw new EntityNotFoundException("There is no book with id: " + id);
        }
        repository.deleteById(id);
    }
}
