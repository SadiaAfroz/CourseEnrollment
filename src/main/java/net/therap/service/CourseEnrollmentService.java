package net.therap.service;

import net.therap.dao.CourseEnrollmentDao;
import net.therap.model.CourseEnrollment;
import net.therap.model.Trainee;

public class CourseEnrollmentService {

    public void enrollTraineeToCourse(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDao courseEnrollmentDao = new CourseEnrollmentDao();
        courseEnrollmentDao.insert(courseEnrollment.getCourse().getId(), courseEnrollment.getTrainee().getId());
    }

    public void deleteTrainee(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDao courseEnrollmentDao = new CourseEnrollmentDao();
        int id = courseEnrollmentDao.findId(courseEnrollment.getCourse().getId(), courseEnrollment.getTrainee().getId());
        courseEnrollmentDao.delete(courseEnrollment.getCourse().getId(), courseEnrollment.getTrainee().getId(), id);
    }

    public void updateTrainee(Trainee oldTrainee, Trainee newTrainee, int courseId) {
        CourseEnrollmentDao courseEnrollmentDao = new CourseEnrollmentDao();
        courseEnrollmentDao.updateTrainee(oldTrainee.getId(), newTrainee.getId(), courseId);
    }
}
