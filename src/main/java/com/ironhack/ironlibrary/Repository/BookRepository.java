package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Book;
import com.ironhack.ironlibrary.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    //Metodo para poder buscar el libro por su isbn
    Optional<Book> findByIsbn(String isbn);
}
