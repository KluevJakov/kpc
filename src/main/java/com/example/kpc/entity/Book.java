package com.example.kpc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

/* сущность справочника */

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    protected String title;
    protected String type;
    protected String author;
    protected String publishYear;
    protected String avatar;
    protected String file;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(type, book.type) && Objects.equals(author, book.author) && Objects.equals(publishYear, book.publishYear) && Objects.equals(avatar, book.avatar) && Objects.equals(file, book.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, author, publishYear, avatar, file);
    }
}
