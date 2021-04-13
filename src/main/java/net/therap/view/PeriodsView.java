package net.therap.view;

import net.therap.model.Period;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/12/21
 */
public class PeriodsView implements DetailsView<Period> {

    @Override
    public void view(List<Period> periods) {
        for (Period period : periods) {
            System.out.println(period.toString());
        }
    }
}
