package com.github.perscholas.model;

import java.io.Serializable;
import java.util.Objects;

public class StudentCourseId implements Serializable {
    private Integer courseId;
    private String studentEmail;

    public StudentCourseId() {
    }

    public StudentCourseId(Integer courseId, String studentEmail) {
        this.courseId = courseId;
        this.studentEmail = studentEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseId that = (StudentCourseId) o;
        return courseId.equals(that.courseId) &&
                studentEmail.equals(that.studentEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentEmail);
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
