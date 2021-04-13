package net.therap.validator;

import net.therap.dao.CourseDao;

import static net.therap.util.Capacity.MAX_COURSE_TO_TRAINEE_ENROLL;

public class CourseValidator {

    private CourseDao courseDao;

    public CourseValidator() {
        courseDao = new CourseDao();
    }

    public boolean isValidName(String courseName) {
        int count = courseDao.checkNameExist(courseName);
        if (count == 0) {
            return true;
        }
        return false;
    }

    public boolean isValidId(int id) {
        int count = courseDao.checkIdExist(id);
        if (count == 0) {
            return false;
        }
        return true;
    }

    public boolean hasCourseCapacity(int traineeId) {
        int countCourses = courseDao.findAllByTraineeId(traineeId).size();
        if (countCourses < MAX_COURSE_TO_TRAINEE_ENROLL) {
            return true;
        }
        return false;
    }
}
