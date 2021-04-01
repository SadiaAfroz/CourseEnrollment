package net.therap;

import net.therap.controller.CourseDetails;
import net.therap.controller.TimeAllocation;
import net.therap.model.Course;
import net.therap.model.Period;
import net.therap.model.Trainee;
import net.therap.view.DetailsView;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 3/30/21
 */
public class Main {
    public static void main(String[] args) {

        CourseDetails courseDetails = new CourseDetails();

        Course course = courseDetails.getCourseById(2);
        course.printCourse();

        List<Trainee> trainees = courseDetails.getTraineesByCourseId(1);
        DetailsView.viewTrainees(trainees);

        List<Period> periods = courseDetails.getAllocatedTimeByCourseId(1);
        DetailsView.viewPeriods(periods);

        TimeAllocation timeAllocation = new TimeAllocation();

        String time1 = "10:00:00";
        String time2 = "10:50:00";
        int dayOfWeek = 3;
        int enrollmentId = 1;
        timeAllocation.allocateTime(time1, time2, dayOfWeek, enrollmentId);
    }
}
