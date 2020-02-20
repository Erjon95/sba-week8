package com.github.perscholas;

import com.github.perscholas.model.StudentCourse;
import com.github.perscholas.model.StudentCourseInterface;
import com.github.perscholas.model.StudentInterface;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JdbcConfigurator {
    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "SBA";
    static EntityManager entityMgr;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    public static void populateTables(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        while (line != null)
        {
            entityMgr.createNativeQuery(line).executeUpdate();
            line = reader.readLine();
        }
        reader.close();
    }

    public static void createTables(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        BufferedReader reader = Files.newBufferedReader(path);
        String statement = "";
        String line = reader.readLine();
        while (line != null)
        {
            statement += line;
            line = reader.readLine();
        }
        entityMgr.createNativeQuery(statement).executeUpdate();
        reader.close();
    }

    public static void useDB()
    {
        entityMgr = getEntityManager();
        entityMgr.getTransaction().begin();
        entityMgr.createNativeQuery("use sbaweek8").executeUpdate();
        entityMgr.getTransaction().commit();
    }

    public static void initialize() throws IOException {

        try {
            List<StudentInterface> list = entityMgr.createNativeQuery("select * from student").getResultList();
        }catch (javax.persistence.PersistenceException e)
        {
            String filePopulateCourses ="src/main/resources/courses.populate-table.sql";
            String filePopulateStudents ="src/main/resources/students.populate-table.sql";
            String fileCreateCourse ="src/main/resources/courses.create-table.sql";
            String fileCreateStudent ="src/main/resources/students.create-table.sql";
            String fileCreateStudentCourse ="src/main/resources/studentcourse.create-table.sql";

            entityMgr.getTransaction().begin();
            createTables(fileCreateCourse);
            createTables(fileCreateStudent);
            createTables(fileCreateStudentCourse);
            populateTables(filePopulateCourses);
            populateTables(filePopulateStudents);
            entityMgr.getTransaction().commit();
            System.out.println("Tables populated");
        }
        return;
    }

    public static void insert(int courseId, String studentEmail)
    {
        StudentCourseInterface studentCourseInterface = new StudentCourse(courseId, studentEmail);
        entityMgr.getTransaction().begin();
        entityMgr.persist(studentCourseInterface);
        entityMgr.getTransaction().commit();
    }
}
