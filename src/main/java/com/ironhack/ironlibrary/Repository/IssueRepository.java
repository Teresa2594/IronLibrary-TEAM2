package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer> {

    Optional<Issue> findByUsnStudent(String usnStudent);
}
