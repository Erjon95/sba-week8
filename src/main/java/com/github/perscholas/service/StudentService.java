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
    public StudentInterface getStudentByEmail(String studentEmail) {
        return null;
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
        }
        return result;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId){
        JdbcConfigurator.insert(courseId, studentEmail);
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
