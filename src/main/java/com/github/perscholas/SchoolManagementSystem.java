package com.github.perscholas;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;

import javax.persistence.EntityManager;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();
    private EntityManager entityManager;

    public SchoolManagementSystem(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        while (true) {
            String smsDashboardInput = getSchoolManagementSystemDashboardInput();
            if ("logout".equals(smsDashboardInput))
                continue;
            if ("login".equals(smsDashboardInput)) {
                StudentDao studentService = new StudentService();
                String studentEmail = console.getStringInput("Enter your email:");
                String studentPassword = console.getStringInput("Enter your password:");
                Boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword, entityManager);
                if (isValidLogin) {
                    String studentDashboardInput = getStudentDashboardInput(studentEmail);
                    if ("logout".equals(studentDashboardInput))
                        continue;
                    if ("register".equals(studentDashboardInput)) {
                        Integer courseId = getCourseRegistryInput();
                        studentService.registerStudentToCourse(studentEmail, courseId, entityManager);
                    }
                }
            }
        }
    }

    private String getSchoolManagementSystemDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("\nWelcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ login ], [ logout ]")
                .toString());
    }

    private String getStudentDashboardInput(String studentEmail) {
        StudentDao studentService = new StudentService();
        studentService.getStudentCourses(studentEmail, entityManager);
        return console.getStringInput(new StringBuilder()
                .append("\nWelcome to the Student Dashboard!")
                .append("\nCourses you are registered to:")
                .append("\n\n" + studentService.toString())
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ register ], [ logout]")
                .toString());
    }


    private Integer getCourseRegistryInput() {
        CourseDao courseService = new CourseService();
        courseService.getAllCourses(entityManager);
        return console.getIntegerInput(new StringBuilder()
                .append("\nWelcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\n" + courseService.toString())
                .toString());
    }

}
