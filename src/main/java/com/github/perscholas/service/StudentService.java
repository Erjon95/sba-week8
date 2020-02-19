package com.github.perscholas.service;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentCourse;
import com.github.perscholas.model.StudentCourseInterface;
import com.github.perscholas.model.StudentInterface;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
    @Transactional
    @Modifying
    public void registerStudentToCourse(String studentEmail, int courseId, EntityManager entityManager) {

        StudentCourseInterface studentCourseInterface = new StudentCourse(courseId, studentEmail);

        entityManager.createNativeQuery("insert into studentcourse (courseid, studentemail) values(?1, ?2);")
        .setParameter(1, courseId).setParameter(2, studentEmail).executeUpdate();

        entityManager.getTransaction().commit();
        //entityManager.persist(studentCourseInterface);
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail, EntityManager entityManager) {

        Query query = entityManager.createNativeQuery("select c.id, c.name, c.instructor from course c join studentcourse sc on c.id = sc.courseid join student s on sc.studentemail = s.email where sc.studentemail = ?1");
        return query.setParameter(1, studentEmail).getResultList();
    }
}
