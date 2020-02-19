package com.github.perscholas.service;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {

    private List<CourseInterface> list;

    public CourseService()
    {
        list = new ArrayList<>();
    }

    @Override
    public void getAllCourses(EntityManager entityManager) {
        TypedQuery<CourseInterface> query = entityManager.createQuery("select c from Course c", CourseInterface.class);
        list = query.getResultList();
    }

    @Override
    public String toString() {
        String result = "";
        for(CourseInterface courseInterface: list)
            result += courseInterface.getId().toString() + " " + courseInterface.getName() + " " + courseInterface.getInstructor() + "\n";
        return result;
    }
}
