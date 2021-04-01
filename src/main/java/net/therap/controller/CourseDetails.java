package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Period;
import net.therap.model.Trainee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sadia.afroz
 * @since 3/31/21
 */
public class CourseDetails {
    MysqlDBMS mysqlConnection;

    public CourseDetails() {
        this.mysqlConnection = new MysqlDBMS();
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT course_name FROM COURSES WHERE course_id= ?";
        Course course=new Course();
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(courseId));
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            course.setCourseId(courseId);
            while (rs.next()) {
                String courseName = rs.getString("course_name");
                course.setCourseName(courseName);
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return course;
    }

    public List<Trainee> getTraineesByCourseId(int courseId){
        List<Trainee> trainees=new ArrayList<>();
        String sql = "SELECT TRAINEES.trainee_id,TRAINEES.trainee_name FROM COURSES\n" +
                "JOIN ENROLLMENT_PAIR  ON COURSES.course_id=ENROLLMENT_PAIR.course_id\n" +
                "JOIN TRAINEES ON TRAINEES.trainee_id=ENROLLMENT_PAIR.trainee_id \n" +
                "WHERE COURSES.course_id=?";
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(courseId));
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                Trainee trainee = new Trainee();
                int traineeId = rs.getInt("trainee_id");
                String traineeName = rs.getString("trainee_name");
                trainee.setTraineeId(traineeId);
                trainee.setTraineeName(traineeName);
                trainees.add(trainee);
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trainees;
    }

    public List<Period> getAllocatedTimeByCourseId(int courseId){
        List<Period> periods =new ArrayList<>();
        String sql = "SELECT T.time_id, TR.start_time, TR.end_time, TR.day_of_week FROM COURSES C\n" +
                "JOIN ENROLLMENT_PAIR E ON C.course_id=E.course_id\n" +
                "JOIN TIME_ALLOCATION T ON E.enrollment_id=T.enrollment_id\n" +
                "JOIN TIME_RANGE TR ON T.time_id=TR.time_id\n" +
                "WHERE C.course_id=?";
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(courseId));
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                Period period = new Period();
                int timeId = rs.getInt("time_id");
                String startTime = rs.getTime("start_time").toString();
                String endTime= rs.getTime("end_time").toString();
                int dayOfWeek=rs.getInt("day_of_week");

                period.setTime_id(timeId);
                period.setStartTime(startTime);
                period.setEndTime(endTime);
                period.setDayOfWeek(dayOfWeek);
                periods.add(period);
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return periods;
    }
}
