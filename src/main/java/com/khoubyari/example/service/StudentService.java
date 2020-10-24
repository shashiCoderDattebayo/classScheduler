package com.khoubyari.example.service;

import com.khoubyari.example.dao.jpa.StudentRepository;
import com.khoubyari.example.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public StudentService() {
    }

    public Student createStudent(Student hotel) {
        return studentRepository.save(hotel);
    }

    public Student getStudent(long id) {
        return studentRepository.findOne(id);
    }

    public void updateStudent(Student hotel) {
        studentRepository.save(hotel);
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<Student> getAllStudents(Integer page, Integer size) {
        Page pageOfStudents = studentRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("Khoubyari.StudentService.getAll.largePayload");
        }
        return pageOfStudents;
    }
}
