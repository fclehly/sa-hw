package com.fwz.controller;

import com.fwz.model.Student;
import com.fwz.service.StudentServiceImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fwz on 2017/6/20.
 */
@RestController
//@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job ExcelFileToDatabaseJob;


    @Autowired
    private StudentServiceImpl studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudents(HttpServletRequest request) {
        int limit = 10;
        int offset = 0;
        try {
            limit = Integer.parseInt(request.getParameter("limit"));
        } catch (Exception e) {
            limit = 10;
        }
        try {
            offset = Integer.parseInt(request.getParameter("offset"));
        } catch (Exception e) {
            offset = 0;
        }
        List<Student> l = studentService.findStudents();
        List<Student> students = new ArrayList<Student>();
        for (int i = offset; i < l.size() && i < offset + limit; i++) {
            students.add(l.get(i));
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(
            @PathVariable("studentId") long studentId) {
        Student student = studentService.findStudentById(studentId);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<Student> postStudent(
            @RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseEntity<Student> postImportStudent (
            HttpServletRequest request,
            @RequestParam("info") MultipartFile file) throws IOException {
//        String filename = file.getOriginalFilename();
//        String prefix = filename.substring(0, filename.indexOf("."));
//        File tmp = new File("./target/classes/" + file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(tmp);
//        fos.write(file.getBytes());
//        fos.close();
        file.transferTo(new File(file.getOriginalFilename()));
        File tmp = new File(file.getOriginalFilename());

        System.out.println(tmp.getAbsoluteFile());
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("path-to-file", tmp.getAbsolutePath())
                    .toJobParameters();
            jobLauncher.run(ExcelFileToDatabaseJob, parameters);
            if (tmp.exists()) {
                tmp.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Student> putStudent(
            @RequestBody Student student) {
        studentService.updateStudent(student);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PATCH)
    public ResponseEntity<Student> patchStudent(
            @RequestBody Student student) {
        studentService.updateStudent(student);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(
            @PathVariable("studentId") long studentId) {
        studentService.removeStudent(studentId);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
}
