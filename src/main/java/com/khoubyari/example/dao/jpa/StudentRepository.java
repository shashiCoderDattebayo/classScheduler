package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    Student findStudentByName(String name);
    Page findAll(Pageable pageable);
}
