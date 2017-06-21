package com.fwz.batch;

import com.fwz.model.Student;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by fwz on 2017/6/21.
 */

public class ExcelFileToDatabaseItemProcessor implements ItemProcessor<String[], Student> {


    @Override
    public Student process(String[] tokens) throws Exception {
        Student student = new Student(
                String.format("%d", (int) Double.parseDouble(tokens[0])),
                tokens[1],
                tokens[2]
        );

        student.setUsual((int) Double.parseDouble(tokens[3]));
        student.setBigJob((int) Double.parseDouble(tokens[4]));
        student.setFinalScore((int) Double.parseDouble(tokens[5]));

        return student;
    }
}

