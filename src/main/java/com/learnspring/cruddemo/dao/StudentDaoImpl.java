package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // from Spring
    public void Save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
        // find(entityClass, Primary Key)
    }

    @Override
    public List<Student> findAll() {
        var query = entityManager.createQuery("SELECT ** FROM Student"); // Used The Class Name in Java Code Not The Table Name in DB.
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        var query = entityManager.createQuery("FROM Student WHERE lastName=:ln", Student.class);

        query.setParameter("ln", lastName);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        final Student student = entityManager.find(Student.class, id);

        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int removeAll() {
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }
}
