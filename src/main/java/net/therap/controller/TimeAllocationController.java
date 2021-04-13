package net.therap.controller;

import net.therap.dao.PeriodDao;
import net.therap.model.Days;
import net.therap.model.Period;
import net.therap.service.PeriodService;
import net.therap.service.TimeAllocationService;
import net.therap.validator.TimeRangeValidator;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

/**
 * @author sadia.afroz
 * @since 4/1/21
 */
public class TimeAllocationController {

    public void allocateTime() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter start time (hh:mm:ss): ");
        String startTime = input.nextLine();

        TimeRangeValidator timeRangeValidator = new TimeRangeValidator();
        while (!timeRangeValidator.isValidateTimeFormat(startTime)) {
            System.out.println(startTime);
            System.out.printf("Wrong Input Format. Enter again: ");
            startTime = input.nextLine();
        }

        System.out.printf("Enter end time (hh:mm:ss): ");
        String endTime = input.nextLine();
        while (!timeRangeValidator.isValidateTimeFormat(endTime)) {
            System.out.printf("Wrong Input Format. Enter again: ");
            endTime = input.nextLine();
        }

        System.out.printf("Enter day of the week: ");
        Days.printDays();
        int dayOfWeek = input.nextInt();

        while (!timeRangeValidator.isValidDayOfWeekFormat(dayOfWeek)) {
            System.out.printf("Wrong Input Format. Enter again: ");
            dayOfWeek = input.nextInt();
        }

        System.out.printf("Enter enrollment id: ");
        int enrollmentId = input.nextInt();

        Time start = Time.valueOf(startTime);
        Time end = Time.valueOf(endTime);

        TimeAllocationService timeAllocationService = new TimeAllocationService();
        timeAllocationService.doTimeAllocation(start, end, dayOfWeek, enrollmentId);
    }

    public void getAllocatedTimeByCourseId() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Course Id: ");
        int courseId = input.nextInt();

        PeriodDao periodDao = new PeriodDao();
        List<Period> periods = periodDao.getPeriodsInfo(courseId);

        PeriodService periodProcessor = new PeriodService();
        periodProcessor.processPeriods(periods);
    }
}
