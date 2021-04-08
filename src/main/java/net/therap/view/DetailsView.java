package net.therap.view;

import net.therap.model.Course;
import net.therap.model.Period;
import net.therap.model.Trainee;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 3/31/21
 */
public class DetailsView {

    public static void viewCourses(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course.toString());
        }
    }

    public static void viewTrainees(List<Trainee> trainees) {
        for (Trainee trainee : trainees) {
            System.out.println(trainee.toString());
        }
    }

    public static void viewPeriods(List<Period> periods) {
        for (Period period : periods) {
            System.out.println(period.toString());
        }
    }

    public static void showStartingOption() {
        System.out.println();
        System.out.println("Choose the option :");
        System.out.println("1. Get course details by course id");
        System.out.println("2. Get trainess details by course id");
        System.out.println("3. Get allocated time slot by course id");
        System.out.println("4. Allocate a time for specific course-trainee by the enrollment id");
        System.out.println("5. EXIT ");
    }
}
