package org.example.repositories;

import org.example.entities.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(UUID.randomUUID());
            String insertSql = "INSERT INTO library.books (id, title, author, publication_year) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertSql, book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear());
        } else {
            String updateSql = "UPDATE library.books SET title = ?, author = ?, publication_year = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
        }
        return book;
    }


    public Book findById(UUID id) {
        String querySql = "SELECT * FROM library.books WHERE id = ?";
        return jdbcTemplate.queryForObject(querySql, new Object[]{id}, (resultSet, rowNumber) -> {
            Book foundBook = new Book();
            foundBook.setId(UUID.fromString(resultSet.getString("id")));
            foundBook.setTitle(resultSet.getString("title"));
            foundBook.setAuthor(resultSet.getString("author"));
            foundBook.setPublicationYear(resultSet.getInt("publication_year"));
            return foundBook;
        });
    }

    public List<Book> findAll() {
        String querySql = "SELECT * FROM library.books";
        return jdbcTemplate.query(querySql, (resultSet, rowNumber) -> {
            Book book = new Book();
            book.setId(UUID.fromString(resultSet.getString("id")));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublicationYear(resultSet.getInt("publication_year"));
            return book;
        });
    }
    public void deleteById(UUID id) {
        String deleteSql = "DELETE FROM library.books WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
}