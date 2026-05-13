package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String name, String email, int age) {
        Student s = new Student(name, email, age);
        em.persist(s);
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
            System.out.println("Deleted student: " + student);
            return true;
        } else {
            System.out.println("Student not found with ID = " + id);
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Transactional(readOnly = true)
    public void printAll() {
        em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList()
                .forEach(System.out::println);
    }
}
