package com.fwz.service;

import com.fwz.model.Student;
import com.fwz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fwz on 2017/6/20.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
//    @Transactional
    public void saveStudent(Student student) {
        repository.save(student);
    }

    @Override
    public Student findStudentById(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Student> findStudents() {
        List<Student> students = (List<Student>) repository.findAll();
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        repository.save(student);
    }

    @Override
    public void removeStudent(long id) {
        repository.delete(id);
    }
}
