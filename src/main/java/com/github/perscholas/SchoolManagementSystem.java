package com.github.perscholas;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.utils.IOConsole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();

    @Override
    public void run() {
        String smsDashboardInput = getSchoolManagementSystemDashboardInput();
        CourseDao courseDao = null;
        if ("login".equals(smsDashboardInput)) {

            try {
                List<CourseInterface> list = courseDao.getAllCourses();
            }catch (NullPointerException e)
            {
                if (courseDao == null)
                    System.out.println("coursedao is null");
            }

            /*for (int i = 0; i < list.size(); i++)
                System.out.println(list.get(i).getName());*/


            /*StudentDao studentService = null; // TODO - get literal value
            String studentEmail = console.getStringInput("Enter your email:");
            String studentPassword = console.getStringInput("Enter your password:");
            Boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
            if (isValidLogin) {
                String studentDashboardInput = getStudentDashboardInput();
                if ("register".equals(studentDashboardInput)) {
                    Integer courseId = getCourseRegistryInput();
                    studentService.registerStudentToCourse(studentEmail, courseId);
                }
            }*/
        }
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
                .append("Welcome to the Student Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ register ], [ logout]")
                .toString());
    }


    private Integer getCourseRegistryInput() {
        List<Integer> listOfCoursesIds = new ArrayList<>();
        return console.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t" + listOfCoursesIds.toString())
                .toString());
    }
}
