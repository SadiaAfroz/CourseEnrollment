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
            course.printCourse();
        }
    }

    public static void viewTrainees(List<Trainee> trainees) {
        for (Trainee trainee : trainees) {
            trainee.printTrainee();
        }
    }

    public static void viewPeriods(List<Period> periods) {
        for (Period period : periods) {
            period.printPeriod();
        }
    }

}
