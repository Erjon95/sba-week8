package com.github.perscholas.dao;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 5:56 PM
 */
public interface CourseDao {
    void getAllCourses(EntityManager entityManager);
}
