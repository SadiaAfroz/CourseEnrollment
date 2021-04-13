package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.CourseEnrollment;
import net.therap.model.Trainee;
import net.therap.service.CourseEnrollmentService;
import net.therap.service.CourseService;
import net.therap.validator.CourseEnrollmentValidator;
import net.therap.validator.CourseValidator;
import net.therap.validator.TraineeValidator;

import java.util.Scanner;

public class CourseEnrollmentController {

    public void enrollTraineeForCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course Id: ");
        int courseId = input.nextInt();
        System.out.println("Enter Trainee Id: ");
        int traineeId = input.nextInt();

        CourseValidator courseValidator = new CourseValidator();
        TraineeValidator traineeValidator = new TraineeValidator();
        if (courseValidator.isValidId(courseId) && traineeValidator.isValidId(traineeId)) {
            if (traineeValidator.hasTraineeCapacity(courseId) && courseValidator.hasCourseCapacity(traineeId)) {
                Course course = new Course(courseId);
                Trainee trainee = new Trainee(traineeId);
                CourseEnrollment courseEnrollment = new CourseEnrollment(course, trainee);
                CourseEnrollmentService courseEnrollmentService = new CourseEnrollmentService();
                courseEnrollmentService.enrollTraineeToCourse(courseEnrollment);
            } else {
                System.out.println("Max Capacity Exceeded");
            }
        } else {
            System.out.println("Invalid id provided");
        }

        CourseService courseProcessor = new CourseService();
        Course course = courseProcessor.getCourse(courseId);
        courseProcessor.processCourse(course);
    }

    public void removeTraineeFromCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Trainee Id: ");
        int traineeId = input.nextInt();
        System.out.println("Enter Course Id: ");
        int courseId = input.nextInt();

        Course course = new Course(courseId);
        Trainee trainee = new Trainee(traineeId);
        CourseEnrollment courseEnrollment = new CourseEnrollment(course, trainee);
        CourseEnrollmentValidator courseEnrollmentValidator = new CourseEnrollmentValidator();
        if (courseEnrollmentValidator.isValid(courseEnrollment)) {
            CourseEnrollmentService courseEnrollmentService = new CourseEnrollmentService();
            courseEnrollmentService.deleteTrainee(courseEnrollment);
        }
    }

    public void updateTraineeNameOfCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Old Trainee Name: ");
        String oldName = input.nextLine();
        System.out.println("Enter New Trainee Name: ");
        String newName = input.nextLine();
        System.out.println("Enter Course Id : ");
        int courseId = input.nextInt();

        CourseEnrollmentValidator courseEnrollmentValidator = new CourseEnrollmentValidator();
        Trainee oldTrainee = new Trainee(oldName);
        Trainee newTrainee = new Trainee(newName);
        if (courseEnrollmentValidator.isValidUpdatePair(oldTrainee, newTrainee, courseId)) {
            CourseEnrollmentService courseEnrollmentService = new CourseEnrollmentService();
            courseEnrollmentService.updateTrainee(oldTrainee, newTrainee, courseId);
        }
    }
}
