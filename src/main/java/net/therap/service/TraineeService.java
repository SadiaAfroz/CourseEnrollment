package net.therap.service;

import net.therap.dao.TraineeDao;
import net.therap.model.Trainee;
import net.therap.view.TraineesView;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class TraineeService {

    public void processTrainees(List<Trainee> trainees) {
        TraineesView traineesView = new TraineesView();
        traineesView.view(trainees);
    }

    public List<Trainee> getTrainees(int courseId) {
        TraineeDao traineeDao = new TraineeDao();
        List<Trainee> trainees = traineeDao.findAllByCourseId(courseId);

        return trainees;
    }

    public void insertTrainee(Trainee trainee) {
        TraineeDao traineeDao = new TraineeDao();
        traineeDao.insert(trainee);
    }
}
