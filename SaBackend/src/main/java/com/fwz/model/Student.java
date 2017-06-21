package com.fwz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by fwz on 2017/6/20.
 */
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String no;
    private String name;
    private String department;

    //成绩
    private int usual;
    private int bigJob;
    private int finalScore;

    public Student() {
    }

    public Student(
            String no,
            String name,
            String department) {
        this.no = no;
        this.name = name;
        this.department = department;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getUsual() {
        return usual;
    }

    public void setUsual(int usual) {
        this.usual = usual;
    }

    public int getBigJob() {
        return bigJob;
    }

    public void setBigJob(int bigJob) {
        this.bigJob = bigJob;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
