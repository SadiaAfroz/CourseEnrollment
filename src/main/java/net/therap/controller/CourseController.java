package net.therap.controller;

import net.therap.model.Course;
import net.therap.service.CourseService;
import net.therap.validator.CourseValidator;

import java.util.List;
import java.util.Scanner;

/**
 * @author sadia.afroz
 * @since 3/31/21
 */
public class CourseController {

    public void getCourseById() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course Id: ");
        int courseId = input.nextInt();

        CourseService courseProcessor = new CourseService();
        Course course = courseProcessor.getCourse(courseId);
        courseProcessor.processCourse(course);
    }

    public void getCoursesByTraineeId() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course Id: ");
        int traineeId = input.nextInt();

        CourseService courseProcessor = new CourseService();
        List<Course> courses = courseProcessor.getCourses(traineeId);
        courseProcessor.processCourses(courses);
    }

    public void insertCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course Name: ");
        String courseName = input.nextLine();
        CourseValidator courseValidator = new CourseValidator();
        if (courseValidator.isValidName(courseName)) {
            Course course = new Course();
            course.setName(courseName);

            CourseService courseService = new CourseService();
            courseService.insertCourse(course);
        } else {
            System.out.println("Not a valid Name ");
        }
    }

    public void updateCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter new Course Name: ");
        String newName= input.nextLine();
        System.out.println("Enter Course id: ");
        int courseId= input.nextInt();

        CourseService courseService = new CourseService();
        Course course =new Course(courseId,newName);
        courseService.updateCourse(course);

    }

    public void deleteCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course id: ");
        int courseId= input.nextInt();
        CourseService courseService = new CourseService();
        Course course =new Course(courseId);
        courseService.deleteCourse(course);
    }
}
