package com.fwz.service;

import com.fwz.model.Student;

import java.util.List;

/**
 * Created by fwz on 2017/6/20.
 */
public interface StudentService {
    public void saveStudent(Student student);

    public Student findStudentById(long id);

    public List<Student> findStudents();

    public void updateStudent(Student student);

    public void removeStudent(long id);
}
