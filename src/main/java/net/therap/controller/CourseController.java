package net.therap.controller;

import net.therap.connector.MysqlDBMS;
import net.therap.dao.CourseDao;
import net.therap.dao.PeriodDao;
import net.therap.dao.TraineeDao;
import net.therap.model.Course;
import net.therap.model.Period;
import net.therap.model.Trainee;
import net.therap.service.CourseService;
import net.therap.service.PeriodService;
import net.therap.service.TraineeService;

import java.util.List;
import java.util.Scanner;

/**
 * @author sadia.afroz
 * @since 3/31/21
 */
public class CourseController {

    MysqlDBMS mysqlConnection;

    public CourseController() {
        this.mysqlConnection = new MysqlDBMS();
    }

    public void getCourseById() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Course Id: ");
        int courseId = input.nextInt();

        CourseDao courseDao = new CourseDao();
        Course course = courseDao.getCourseInfo(courseId);

        CourseService courseProcessor = new CourseService();
        courseProcessor.processCourse(course);
    }

    public void getTraineesByCourseId() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Course Id: ");
        int courseId = input.nextInt();

        TraineeDao traineeDao = new TraineeDao();
        List<Trainee> trainees = traineeDao.getTraineesInfo(courseId);

        TraineeService traineeProcessor = new TraineeService();
        traineeProcessor.processTrainees(trainees);
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
