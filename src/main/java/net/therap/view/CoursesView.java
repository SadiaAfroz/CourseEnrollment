package net.therap.view;

import net.therap.model.Course;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/12/21
 */
public class CoursesView implements DetailsView<Course> {

    @Override
    public void view(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course.toString());
        }
    }
}
