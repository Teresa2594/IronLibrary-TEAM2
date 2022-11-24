package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    Optional<Book> findByTitle(String title);
}
