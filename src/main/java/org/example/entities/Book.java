package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    private UUID id;
    private String title;
    private String author;
    private int publicationYear;

}
