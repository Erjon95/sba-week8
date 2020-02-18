package com.github.perscholas;

import com.github.perscholas.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainApplication {

    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "SBA";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }
    public static void main(String[] args) {
        //JdbcConfigurator.initialize();
        //Runnable sms = new SchoolManagementSystem();
        //sms.run();
        EntityManager entityMgr = getEntityManager();
        entityMgr.getTransaction().begin();

        Course course = new Course();
        course.setId(102);
        course.setName("Physics");
        course.setInstructor("Frank Drebin");
        entityMgr.persist(course);

        entityMgr.getTransaction().commit();

        entityMgr.clear();
        System.out.println("Record Successfully Inserted In The Database");
    }
}
