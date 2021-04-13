package net.therap.validator;

import net.therap.dao.TraineeDao;

import static net.therap.util.Capacity.MAX_TRAINEE_TO_COURSE_ENROLL;

public class TraineeValidator {
    private TraineeDao traineeDao;

    public TraineeValidator() {
        traineeDao = new TraineeDao();
    }

    public boolean isValidName(String traineeName) {
        int count = traineeDao.checkNameExist(traineeName);
        if (count == 0) {
            return true;
        }
        return false;
    }

    public boolean isValidId(int id) {
        int count = traineeDao.checkIdExist(id);
        if (count == 0) {
            return false;
        }
        return true;
    }

    public boolean hasTraineeCapacity(int courseId) {
        int countTrainees = traineeDao.findAllByCourseId(courseId).size();
        if (countTrainees < MAX_TRAINEE_TO_COURSE_ENROLL) {
            return true;
        }
        return false;
    }
}
