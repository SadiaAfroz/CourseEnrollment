package net.therap.service;

import net.therap.model.Period;
import net.therap.view.DetailsView;

import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class PeriodService {

    public void processPeriods(List<Period> periods) {
        if (periods == null) {
            System.out.println("No Periods available");
        } else {
            DetailsView.viewPeriods(periods);
        }
    }
}
