package com.learnspring.cruddemo;

import com.learnspring.cruddemo.dao.StudentDAO;
import com.learnspring.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    // it executes after the spring beans have been loaded
    // The CommandLineRunner runs after the Spring application context has been fully initialized
    // but before the application starts processing requests.
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
//            readStudent(studentDAO);
//            findAllStudents(studentDAO);
//            findStudentByLastName(studentDAO);
//            updateStudent(studentDAO);
//            removeStudent(studentDAO);
            removeAllStudents(studentDAO);
        };
    }

    private void removeAllStudents(StudentDAO studentDAO) {
        studentDAO.removeAll();
        System.out.println("REMOVED");
    }

    private void removeStudent(StudentDAO studentDAO) {
        studentDAO.remove(1);
    }

    private void updateStudent(StudentDAO studentDAO) {
        var student = studentDAO.findById(1);

        student.setEmail("edited email");

        studentDAO.update(student);

        System.out.println("UPDATED");
    }

    private void findStudentByLastName(StudentDAO studentDAO) {
        var stds = studentDAO.findByLastName("Hosnym");
        for(var std: stds){
            System.out.println(std.getLastName());
        }
    }

    private void findAllStudents(StudentDAO studentDAO) {
        var stds = studentDAO.findAll();
        System.out.println(stds.size());

        for (var std : stds) {
            System.out.println(std.toString());
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        final Student student = studentDAO.findById(1);
        System.out.println(student.toString());
    }

    private void createStudent(StudentDAO studentDAO) {
        Student student = new Student("Omar", "Hosny", "omar@ya.co");

        studentDAO.Save(student);

        System.out.println("SAVED CHECK YOUR ID: " + student.getId());
    }
}
