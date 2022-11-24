package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer> {
}
