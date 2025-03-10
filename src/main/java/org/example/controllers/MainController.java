package org.example.controllers;


import jakarta.validation.Valid;
import org.example.entities.Book;
import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;


@RestController
public class MainController {
    private final BookService service;
    private static final String ID = "/{id}";
    @Autowired
    public MainController(BookService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(service.getAllBooks(pageable));
    }

    @GetMapping(ID)
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = service.createBook(book);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedBook.getId())
                        .toUri()
        ).body(savedBook);
    }

    @PutMapping(ID)
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @Valid @RequestBody Book book) {
        Book updatedBook = service.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(ID)
    public void deleteBook(@PathVariable UUID id) {
        service.deleteBook(id);
    }

}
