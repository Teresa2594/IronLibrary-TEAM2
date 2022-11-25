package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Author;
import com.ironhack.ironlibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Optional<Author> findByName(String author);
    List<Book>findAllByAuthorBook(String author);
    Book findByAuthorBook(String author);

}
