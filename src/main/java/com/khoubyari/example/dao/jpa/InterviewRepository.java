package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Interview;
import com.khoubyari.example.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface InterviewRepository extends PagingAndSortingRepository<Interview, Long> {
    List<Interview> findAllInterviewsByStudentId(long id);
    List<Interview> findAllInterviewsByInterviewerId(long id);
    Page findAll(Pageable pageable);
}
