package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void Save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void remove(Integer id);

    int removeAll();
}
