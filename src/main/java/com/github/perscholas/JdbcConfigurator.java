package com.github.perscholas;

import com.github.perscholas.model.StudentCourse;
import com.github.perscholas.model.StudentCourseInterface;
import com.github.perscholas.model.StudentInterface;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Metamodel;
import java.sql.*;
//import com.mysql.cj.jdbc.Driver;
import java.util.List;
import java.util.StringJoiner;


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

    public static void useDB()
    {
        entityMgr = getEntityManager();
        entityMgr.getTransaction().begin();
        entityMgr.createNativeQuery("use sbaweek8").executeUpdate();
        entityMgr.getTransaction().commit();
    }

    public static void initialize()
    {
        entityMgr.createNativeQuery("CREATE TABLE if not exists `course` (`id` INT(11) NOT NULL,`name` VARCHAR(50) NOT NULL,`instructor` VARCHAR(50) NOT NULL, PRIMARY KEY (`id`));").executeUpdate();
        entityMgr.createNativeQuery("CREATE TABLE if not exists `student` (`email` varchar(50) NOT NULL,`name` VARCHAR(50) NOT NULL,`password` VARCHAR(50) NOT NULL, PRIMARY KEY (`email`));").executeUpdate();
        entityMgr.createNativeQuery("CREATE TABLE if not exists `studentcourse` (\n" +
                "\t`courseid` INT(11) NOT NULL,\n" +
                "\t`studentemail` VARCHAR(50) NOT NULL,\n" +
                "\tPRIMARY KEY (`courseid`, `studentemail`),\n" +
                "\tINDEX `studentemail` (`studentemail`),\n" +
                "\tCONSTRAINT `studentcourse_ibfk_1` FOREIGN KEY (`studentemail`) REFERENCES `student` (`email`) ON DELETE CASCADE,\n" +
                "\tCONSTRAINT `studentcourse_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE\n" +
                ");").executeUpdate();


            entityMgr.createNativeQuery("insert into student (email, name, password) values ('hluckham0@google.ru', 'Hazel Luckham', 'X1uZcoIh0dj');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('sbowden1@yellowbook.com', 'Sonnnie Bowden', 'SJc4aWSU');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('qllorens2@howstuffworks.com', 'Quillan Llorens', 'W6rJuxd');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('cstartin3@flickr.com', 'Clem Startin', 'XYHzJ1S');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('tattwool4@biglobe.ne.jp', 'Thornie Attwool', 'Hjt0SoVmuBz');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('hguerre5@deviantart.com', 'Harcourt Guerre', 'OzcxzD1PGs');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('htaffley6@columbia.edu', 'Holmes Taffley', 'xowtOQ');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('aiannitti7@is.gd', 'Alexandra Iannitti', 'TWP4hf5j');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('ljiroudek8@sitemeter.com', 'Laryssa Jiroudek', 'bXRoLUP');").executeUpdate();
            entityMgr.createNativeQuery("insert into Student (email, name, password) values ('cjaulme9@bing.com', 'Cahra Jaulme', 'FnVklVgC6r6');").executeUpdate();

            entityMgr.createNativeQuery("insert  into Course (id, name, instructor) values (1, 'English', 'Anderea Scamaden');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (2, 'Mathematics', 'Eustace Niemetz');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (3, 'Anatomy', 'Reynolds Pastor');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (4, 'Organic Chemistry', 'Odessa Belcher');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (5, 'Physics', 'Dani Swallow');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (6, 'Digital Logic', 'Glenden Reilingen');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (7, 'Object Oriented Programming','Giselle Ardy');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (8, 'Data Structures', 'Carolan Stoller');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (9, 'Politics', 'Carmita De Maine');").executeUpdate();
            entityMgr.createNativeQuery("insert into Course (id, name, instructor) values (10, 'Art', 'Kingsly Doxsey');").executeUpdate();
    }

    public static void test() {

        try {
            List<StudentInterface> list = entityMgr.createNativeQuery("select * from student").getResultList();
        }catch (javax.persistence.PersistenceException e)
        {
            System.out.println("Persistence Exception");
            entityMgr.getTransaction().begin();
            initialize();
            entityMgr.getTransaction().commit();
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
