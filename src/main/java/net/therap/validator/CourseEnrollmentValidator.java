package net.therap.validator;

import net.therap.dao.CourseEnrollmentDao;
import net.therap.dao.TraineeDao;
import net.therap.model.CourseEnrollment;
import net.therap.model.Trainee;

public class CourseEnrollmentValidator {

    private CourseEnrollmentDao courseEnrollmentDao;

    public CourseEnrollmentValidator() {
        this.courseEnrollmentDao = new CourseEnrollmentDao();
    }

    public boolean isValid(CourseEnrollment courseEnrollment) {
        int count = courseEnrollmentDao.isExist(courseEnrollment.getCourse().getId(), courseEnrollment.getTrainee().getId());
        if (count == 0) {
            return false;
        }
        return true;
    }

    public boolean isValidUpdatePair(Trainee oldTrainee, Trainee newTrainee, int courseId) {
        TraineeDao traineeDao = new TraineeDao();
        int oldTraineeId = traineeDao.findByName(oldTrainee.getName());
        int newTraineeId = traineeDao.findByName(newTrainee.getName());

        if (oldTraineeId != -1 && newTraineeId != -1) {
            oldTrainee.setId(oldTraineeId);
            newTrainee.setId(newTraineeId);
            return true;
        }
        return false;
    }
}
