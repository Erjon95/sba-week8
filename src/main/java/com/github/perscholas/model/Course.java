package com.github.perscholas.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course implements CourseInterface{

    @Id
    private Integer id;
    private String name;
    private String instructor;

    public Course(){}

    public Course (Integer id, String name, String instructor)
    {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInstructor() {
        return instructor;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
