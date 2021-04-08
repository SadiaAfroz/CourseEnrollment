package net.therap.validator;

import net.therap.dao.CoursePeriodDao;

import java.sql.Time;
import java.util.regex.Pattern;

public class TimeRangeValidator {

    CoursePeriodDao coursePeriodDao;

    public TimeRangeValidator(CoursePeriodDao coursePeriodDao) {
        this.coursePeriodDao = coursePeriodDao;
    }

    public TimeRangeValidator() {
    }

    public boolean validateFreeTime(Time startTime, Time endTime, int dayOfWeek) {
        return coursePeriodDao.verifyFreeTimeRange(startTime, endTime, dayOfWeek);
    }

    public boolean validateAlreadyNotAllocated(Time startTime, Time endTime, int dayOfWeek) {
        return coursePeriodDao.verifyAlreadyNotAllocated(startTime, endTime, dayOfWeek);
    }

    public boolean isValidEnrollmentId(int enrollmentId) {
        int count = coursePeriodDao.findEnrollmentId(enrollmentId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidateTimeFormat(String time) {
        boolean valid = true;
        String[] splittedTime = time.split(":");
        if (splittedTime.length == 3) {
            for (String s : splittedTime) {
                if ((s.length() != 2) || !(Pattern.matches("\\d+", s))) {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }
        return valid;
    }

    public boolean isValidDayOfWeekFormat(int dayOfWeek) {
        if (dayOfWeek >= 1 && dayOfWeek <= 5) {
            return true;
        } else {
            return false;
        }
    }
}
