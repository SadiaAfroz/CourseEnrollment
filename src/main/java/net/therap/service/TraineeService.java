package net.therap.service;

import net.therap.model.Trainee;
import net.therap.view.DetailsView;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class TraineeService {

    public void processTrainees(List<Trainee> trainees) {
        DetailsView.viewTrainees(trainees);
    }
}
