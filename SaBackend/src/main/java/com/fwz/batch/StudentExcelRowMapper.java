package com.fwz.batch;

import com.fwz.model.Student;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

/**
 * Created by fwz on 2017/6/21.
 */
public class StudentExcelRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(RowSet rowSet) throws Exception {
        Student student = new Student();

        student.setNo(rowSet.getColumnValue(0));
        student.setName(rowSet.getColumnValue(1));
//        student.setPurchasedPackage(rowSet.getColumnValue(2));

        return student;
    }
}
