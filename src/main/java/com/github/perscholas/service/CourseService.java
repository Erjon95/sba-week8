package com.github.perscholas.service;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.CourseInterface;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {
    EntityManager entityManager = JdbcConfigurator.getEntityManager();
    @Override
    public List<CourseInterface> getAllCourses() {
        
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("use sbaweek8").executeUpdate();
        return entityManager.createNativeQuery("select * from course;").getResultList();
    }


}
