package net.therap.dao;

import net.therap.connector.MysqlConnector;
import net.therap.model.Period;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class PeriodDao {

    private MysqlConnector mysqlConnection;

    public PeriodDao() {
        this.mysqlConnection = new MysqlConnector();
    }

    public List<Period> getPeriodsInfo(int courseId) {
        List<Period> periods = new ArrayList<>();
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
                String endTime = rs.getTime("end_time").toString();
                int dayOfWeek = rs.getInt("day_of_week");

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
