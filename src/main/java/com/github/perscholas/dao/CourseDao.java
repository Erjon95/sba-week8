package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 5:56 PM
 */
public interface CourseDao extends CrudRepository<CourseInterface, Integer> {
    @Query(value = "select * from Course c", nativeQuery = true)
    List<CourseInterface> getAllCourses();
}
