package net.therap;

import net.therap.controller.CourseController;
import net.therap.controller.CourseEnrollmentController;
import net.therap.controller.TimeAllocationController;
import net.therap.controller.TraineeController;
import net.therap.view.StartingOptionsView;

import java.util.Scanner;

import static net.therap.util.InputType.*;

/**
 * @author sadia.afroz
 * @since 3/30/21
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StartingOptionsView.viewOptions();
        while (input.hasNext()) {
            int choice = input.nextInt();
            switch (choice) {
                case COURSE_DETAILS_BY_COURSEID:
                    System.out.println("COURSE_DETAILS_BY_COURSEID");
                    CourseController courseDetails = new CourseController();
                    courseDetails.getCourseById();
                    break;
                case TRAINEES_DETAILS_BY_COURSEID:
                    System.out.println("TRAINEES_DETAILS_BY_COURSEID");
                    TraineeController traineesForCourse = new TraineeController();
                    traineesForCourse.getTraineesByCourseId();
                    break;
                case ADD_NEW_COURSE:
                    CourseController courseAdd = new CourseController();
                    courseAdd.insertCourse();
                    break;
                case ADD_NEW_TRAINEE:
                    TraineeController traineeAdd = new TraineeController();
                    traineeAdd.insertTrainee();
                    break;
                case ADD_NEW_ENROLLMENT:
                    System.out.println("ADD_NEW_ENROLLMENT");
                    CourseEnrollmentController courseEnrollmentController = new CourseEnrollmentController();
                    courseEnrollmentController.enrollTraineeForCourse();
                    break;
                case REMOVE_TRAINEE_FROM_COURSE:
                    System.out.println("REMOVE_TRAINEE_FROM_COURSE");
                    CourseEnrollmentController deleteCourseEnrollment = new CourseEnrollmentController();
                    deleteCourseEnrollment.removeTraineeFromCourse();
                    break;
                case UPDATE_TRAINEE_ON_COURSE:
                    System.out.println("UPDATE_TRAINEE_ON_COURSE");
                    CourseEnrollmentController updateCourseEnrollment = new CourseEnrollmentController();
                    updateCourseEnrollment.updateTraineeNameOfCourse();
                    break;
                case TIMESLOTS_BY_COURSEID:
                    System.out.println("TIMESLOTS_BY_COURSEID");
                    TimeAllocationController allocatedTimeForCourse = new TimeAllocationController();
                    allocatedTimeForCourse.getAllocatedTimeByCourseId();
                    break;
                case ALLOCATE_TIMESLOT:
                    System.out.println("ALLOCATE_TIMESLOT");
                    TimeAllocationController timeAllocation = new TimeAllocationController();
                    timeAllocation.allocateTime();
                    break;
                case UPDATE_TRAINEE_NAME:
                    System.out.println("UPDATE_TRAINEE_NAME");
                    TraineeController traineeUpdate = new TraineeController();
                    traineeUpdate.updateTrainee();
                    break;
                case UPDATE_COURSE_NAME:
                    System.out.println("UPDATE_COURSE_NAME");
                    CourseController courseUpdate = new CourseController();
                    courseUpdate.updateCourse();
                    break;
                case DELETE_TRAINEE:
                    System.out.println("DELETE_TRAINEE");
                    TraineeController traineeDelete = new TraineeController();
                    traineeDelete.deleteTrainee();
                    break;
                case DELETE_COURSE:
                    System.out.println("DELETE_COURSE");
                    CourseController courseDelete = new CourseController();
                    courseDelete.deleteCourse();
                    break;
                case EXIT:
                    System.out.printf("EXIT");
                    System.exit(0);
            }
            StartingOptionsView.viewOptions();
            ;
        }
    }
}
