package net.therap.dao;

import net.therap.connector.MysqlConnector;

import java.sql.*;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class CoursePeriodDao {

    private MysqlConnector mysqlConnection;

    public CoursePeriodDao() {
        this.mysqlConnection = new MysqlConnector();
    }

    public boolean verifyFreeTimeRange(Time startTime, Time endTime, int dayOfWeek) {
        String sql = "SELECT COUNT(*) AS count FROM TIME_RANGE\n" +
                "WHERE ?>=start_time AND ?<=end_time \n" +
                "AND ?>=start_time AND ?<=end_time \n" +
                "AND day_of_week=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTime(1, startTime);
            pst.setTime(2, startTime);
            pst.setTime(3, endTime);
            pst.setTime(4, endTime);
            pst.setInt(5, dayOfWeek);
            ResultSet rs = pst.executeQuery();
            System.out.println("Free TimeRange verified......\n");
            while (rs.next()) {
                count = rs.getInt("count");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (count == 0) ? true : false;
    }

    public void createTimeRange(Time startTime, Time endTime, int dayOfWeek) {
        String sql = "INSERT INTO TIME_RANGE(start_time, end_time,day_of_week)\n" +
                "VALUES(?,?,?)";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTime(1, startTime);
            pst.setTime(2, endTime);
            pst.setString(3, Integer.toString(dayOfWeek));
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("New Time Range Added.....");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public int findTimeId(Time startTime, Time endTime, int dayOfWeek) {
        String sql = "SELECT time_id FROM TIME_RANGE\n" +
                "WHERE ?=start_time AND ?=end_time\n" +
                "AND day_of_week=?";
        int timeId = -1;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTime(1, startTime);
            pst.setTime(2, endTime);
            pst.setInt(3, dayOfWeek);
            ResultSet rs = pst.executeQuery();
            System.out.println("Time Id checked......\n");
            while (rs.next()) {
                timeId = rs.getInt("time_id");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return timeId;
    }

    public int findEnrollmentId(int enrollmentId) {
        String sql = "SELECT COUNT(*) as count FROM ENROLLMENT_PAIR \n" +
                "WHERE enrollment_id=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, enrollmentId);
            ResultSet rs = pst.executeQuery();
            System.out.println("Enrollment id checked......\n");
            while (rs.next()) {
                count = rs.getInt("count");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public void enrollTraineeByEnrollmentId(int timeId, int enrollmentId) {
        String sql = "INSERT INTO TIME_ALLOCATION(time_id, enrollment_id)\n" +
                "VALUES(?,?)";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(timeId));
            pst.setString(2, Integer.toString(enrollmentId));
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("Enrollment Successful.....");
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public boolean verifyAlreadyNotAllocated(Time startTime, Time endTime, int dayOfWeek) {
        String sql = "SELECT COUNT(*) AS count FROM TIME_ALLOCATION T\n" +
                "JOIN TIME_RANGE TR ON TR.time_id=T.time_id\n" +
                "WHERE ?>=TR.start_time AND ?<=TR.end_time \n" +
                "AND ?>=TR.start_time AND ?<=TR.end_time\n" +
                "AND TR.day_of_week=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTime(1, startTime);
            pst.setTime(2, startTime);
            pst.setTime(3, endTime);
            pst.setTime(4, endTime);
            pst.setInt(5, dayOfWeek);
            ResultSet rs = pst.executeQuery();
            System.out.println("Allocated time verified......\n");
            while (rs.next()) {
                count = rs.getInt("count");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (count == 0) ? true : false;
    }
}
