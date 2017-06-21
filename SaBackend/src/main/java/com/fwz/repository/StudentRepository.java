package com.fwz.repository;

import com.fwz.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fwz on 2017/6/20.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}
