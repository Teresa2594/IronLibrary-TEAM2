package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Book;
import com.ironhack.ironlibrary.Model.Issue;
import com.ironhack.ironlibrary.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer> {

    Optional<Issue> findByStudent(Student student);

    List<Issue> findAllByBook (Book book);
}
