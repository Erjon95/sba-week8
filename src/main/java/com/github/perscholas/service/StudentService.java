package com.github.perscholas.service;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {
    private List <CourseInterface> listOfCourses;

    public StudentService()
    {
        listOfCourses = new ArrayList<>();
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        return null;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail, EntityManager entityManager) {
         return entityManager.createQuery("select s from Student s where s.email = ?1", StudentInterface.class)
                .setParameter(1, studentEmail)
                .getSingleResult();

    }

    @Override
    public Boolean validateStudent(String studentEmail, String password, EntityManager entityManager) {

        Boolean result = true;

        Query query = entityManager.createNativeQuery("select * from student where email = ?1 and password = ?2");

        try {
            query.setParameter(1, studentEmail)
                    .setParameter(2, password).getSingleResult();
        }catch (NoResultException nre)
        {
            result = false;
            System.out.println("Wrong credentials");
        }
        return result;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId, EntityManager entityManager){
        int max = (int)entityManager.createNativeQuery("select max(id) from course;").getSingleResult();
        int min = (int)entityManager.createNativeQuery("select min(id) from course;").getSingleResult();

        if (courseId > max || courseId < min) {
            System.out.println("That course does not exists! ");
            return;
        }

        try {
            entityManager.createNativeQuery("select * from studentcourse where courseid = ?1 and studentemail = ?2")
                    .setParameter(1, courseId)
                    .setParameter(2, studentEmail)
                    .getSingleResult();
        }catch (NoResultException nre)
        {
            JdbcConfigurator.insert(courseId, studentEmail);
            System.out.println("Registration successful");
            return;
        }
        System.out.println("You are already registered for that course.");
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail, EntityManager entityManager) {
        listOfCourses = entityManager.createQuery("select c from StudentCourse sc, Course c, Student s where sc.courseId = c.id and sc.studentEmail = s.email and s.email = ?1", CourseInterface.class)
                .setParameter(1, studentEmail)
                .getResultList();
        return listOfCourses;
    }

    @Override
    public String toString() {
        String result = "";
        for(CourseInterface courseInterface: listOfCourses)

            result += courseInterface.getId().toString() + " " + courseInterface.getName() + " " + courseInterface.getInstructor() + "\n";
        return result;
    }
}
