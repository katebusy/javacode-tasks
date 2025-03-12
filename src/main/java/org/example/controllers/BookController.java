package org.example.controllers;

import org.example.entities.Book;
import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final String ID = "/{id}";
    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = service.createBook(book);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdBook.getId())
                        .toUri()
        ).body(createdBook);
    }

    @GetMapping(ID)
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        Book book = service.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PutMapping(ID)
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody Book book) {
        return ResponseEntity.ok(service.updateBook(id, book));
    }

    @DeleteMapping(ID)
    public void deleteBook(@PathVariable UUID id) {
        service.deleteBook(id);
    }
}
