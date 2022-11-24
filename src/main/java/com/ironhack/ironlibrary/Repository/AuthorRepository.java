package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Author;
import com.ironhack.ironlibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    // List all books along with author
    List<Book> findAllByAuthorBook(String author);

    //Metodo para buscar un autor por nombre
    Optional<Author> findByName (String name);

}
