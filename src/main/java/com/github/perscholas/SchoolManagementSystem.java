package com.github.perscholas;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;

import javax.persistence.EntityManager;
import java.util.List;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();
    private EntityManager entityManager;

    public SchoolManagementSystem(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        String smsDashboardInput;
        while (true){
            smsDashboardInput = getSchoolManagementSystemDashboardInput();
            if("logout".equals(smsDashboardInput))
                continue;
            if ("login".equals(smsDashboardInput)) {
                StudentDao studentService = new StudentService();
                String studentEmail = console.getStringInput("Enter your email:");
                String studentPassword = console.getStringInput("Enter your password:");
                Boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword, entityManager);
                if (isValidLogin) {
                    String studentDashboardInput = getStudentDashboardInput();
                    if ("logout".equals(studentDashboardInput))
                        continue;
                    if ("register".equals(studentDashboardInput)) {
                        Integer courseId = getCourseRegistryInput(entityManager);
                        studentService.registerStudentToCourse(studentEmail, courseId, entityManager);
                        String studentCourseViewInput = getCourseViewInput();
                        if ("logout".equals(studentCourseViewInput))
                            continue;
                        if ("view".equals(studentCourseViewInput)) {
                            studentService.getStudentCourses(studentEmail, entityManager);
                            console.println(new StringBuilder()
                                    .append("[ %s ] is registered to the following courses:")
                                    .append("\n" + studentService.toString())
                                    .toString(), studentService.getStudentByEmail(studentEmail, entityManager).getName());
                        }
                    }
                }
            }
        }
    }

    private String getCourseViewInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Course View Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ view ], [ logout ]")
                .toString());
    }

    private String getSchoolManagementSystemDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ login ], [ logout ]")
                .toString());
    }

    private String getStudentDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ register ], [ logout]")
                .toString());
    }


    private Integer getCourseRegistryInput(EntityManager entityManager) {
        CourseDao courseDao = new CourseService();
        courseDao.getAllCourses(entityManager);
        return console.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n" + courseDao
                        .toString()
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll(", ", "\n\t"))
                .toString());
    }

}
