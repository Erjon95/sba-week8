package com.github.perscholas.service;

import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;

import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {
    @Override
    public List<StudentInterface> getAllStudents() {
        return null;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        return null;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {

    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
