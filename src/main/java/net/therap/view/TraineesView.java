package net.therap.view;

import net.therap.model.Trainee;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/12/21
 */
public class TraineesView implements DetailsView<Trainee> {

    @Override
    public void view(List<Trainee> trainees) {
        for (Trainee trainee : trainees) {
            System.out.println(trainee.toString());
        }
    }
}
