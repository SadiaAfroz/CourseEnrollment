package net.therap.service;

import net.therap.dao.CourseDao;
import net.therap.model.Course;
import net.therap.view.CoursesView;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class CourseService {

    public void processCourse(Course course) {
        System.out.println(course.toString());
    }

    public void processCourses(List<Course> courses) {
        CoursesView coursesView = new CoursesView();
        coursesView.view(courses);
    }

    public Course getCourse(int courseId) {
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findById(courseId);

        return course;
    }

    public List<Course> getCourses(int traineeId) {
        CourseDao courseDao = new CourseDao();
        List<Course> courses = courseDao.findAllByTraineeId(traineeId);

        return courses;
    }

    public void insertCourse(Course course) {
        CourseDao courseDao = new CourseDao();
        courseDao.insert(course);
    }
}
