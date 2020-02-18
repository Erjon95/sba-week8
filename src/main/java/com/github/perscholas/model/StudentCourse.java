package com.github.perscholas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StudentCourseId.class)
public class StudentCourse implements StudentCourseInterface {
    @Id
    private Integer courseId;
    @Id
    private String studentEmail;

    public StudentCourse() {
    }

    public StudentCourse(Integer courseId, String studentEmail) {
        this.courseId = courseId;
        this.studentEmail = studentEmail;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
