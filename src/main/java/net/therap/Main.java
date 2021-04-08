package net.therap;

import net.therap.controller.CourseController;
import net.therap.controller.TimeAllocation;
import net.therap.view.DetailsView;

import java.util.Scanner;

import static net.therap.controller.InputType.*;

/**
 * @author sadia.afroz
 * @since 3/30/21
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        DetailsView.showStartingOption();
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
                    CourseController traineesForCourse = new CourseController();
                    traineesForCourse.getTraineesByCourseId();
                    break;
                case TIMESLOTS_BY_COURSEID:
                    System.out.println("TIMESLOTS_BY_COURSEID");
                    CourseController periodsForCourse = new CourseController();
                    periodsForCourse.getAllocatedTimeByCourseId();
                    break;
                case ALLOCATE_TIMESLOT:
                    System.out.println("ALLOCATE_TIMESLOT");
                    TimeAllocation timeAllocation = new TimeAllocation();
                    timeAllocation.allocateTime();
                    break;
                case EXIT:
                    System.out.printf("EXIT");
                    System.exit(0);
            }
            DetailsView.showStartingOption();
        }
    }
}
