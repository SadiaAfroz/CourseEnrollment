package net.therap.service;

import net.therap.dao.CoursePeriodDao;
import net.therap.validator.TimeRangeValidator;

import java.sql.Time;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class TimeAllocationService {

    public void doTimeAllocation(Time start, Time end, int dayOfWeek, int enrollmentId) {
        CoursePeriodDao coursePeriodDao = new CoursePeriodDao();
        TimeRangeValidator timeRangeValidator = new TimeRangeValidator(coursePeriodDao);

        if (timeRangeValidator.validateFreeTime(start, end, dayOfWeek)) { // No specified time range created
            coursePeriodDao.createTimeRange(start, end, dayOfWeek);
            if (timeRangeValidator.isValidEnrollmentId(enrollmentId)) {
                int newTimeId = coursePeriodDao.findTimeId(start, end, dayOfWeek);
                coursePeriodDao.enrollTraineeByEnrollmentId(newTimeId, enrollmentId);
            } else {
                System.out.printf("Invalid Enrollment Id ");
            }
        } else if (timeRangeValidator.validateAlreadyNotAllocated(start, end, dayOfWeek)) { // this time range already not allocated

            int timeId = coursePeriodDao.findTimeId(start, end, dayOfWeek);
            if (timeId == -1) {
                System.out.println("This Time Allocation is not possible......");
            } else {
                if (timeRangeValidator.isValidEnrollmentId(enrollmentId)) {
                    coursePeriodDao.enrollTraineeByEnrollmentId(timeId, enrollmentId);
                } else {
                    System.out.printf("Invalid Enrollment Id ");
                }
            }
        } else {
            System.out.println("Already allocated......");
        }
    }
}
