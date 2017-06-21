package com.fwz.batch;

import com.fwz.model.Student;
import com.fwz.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fwz on 2017/6/21.
 */
public class ExcelFileToDatabaseItemWriter implements ItemWriter<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFileToDatabaseItemWriter.class);

    @Autowired
    private StudentService studentService;

    @Override
    public void write(List<? extends Student> list) throws Exception {
        list.forEach(student-> {
            studentService.saveStudent(student);
        });
    }
}
