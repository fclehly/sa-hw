package com.fwz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by fwz on 2017/6/19.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(StudentRepository resposity) {
//        return (args) -> {
//            resposity.save(
//                    new Student("141220900", "aa", "cs"));
//            resposity.save(
//                    new Student("141220800", "bb", "cs"));
//            resposity.save(
//                    new Student("141220700", "cc", "cs"));
////            Student student = resposity.findOne(new Long(1));
//            System.out.println("--------------------------");
//            System.out.println("--------------------------");
////            System.out.println(student.getName());
//
//        };
//    }
}
