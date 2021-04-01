package net.therap.controller;

import java.sql.*;

/**
 * @author sadia.afroz
 * @since 4/1/21
 */
public class TimeAllocation {
    MysqlDBMS mysqlConnection;

    public TimeAllocation() {
        this.mysqlConnection = new MysqlDBMS();
    }

    public void allocateTime(String startTime, String endTime, int dayOfWeek, int enrollmentId) {
        Time start = Time.valueOf(startTime);
        Time end = Time.valueOf(endTime);
        if (verifyFreeTime(start, end, dayOfWeek)) { // No specified time range created
            createTimeRange(start, end, dayOfWeek);
            int newTimeId = findTimeId(start, end, dayOfWeek);
            enrollTraineeByEnrollmentId(newTimeId, enrollmentId);
        } else if (verifyAlreadyNotAllocated(start, end, dayOfWeek)) { // this time range already not allocated
            int timeId = findTimeId(start, end, dayOfWeek);
            if (timeId == 0) {
                System.out.println("This Time Allocation is not possible......");
            } else {
                enrollTraineeByEnrollmentId(timeId, enrollmentId);
            }
        }
        else{
            System.out.println("Already allocated......");
        }

    }

    public void enrollTraineeByEnrollmentId(int timeId, int enrollmentId) {
        String sql = "INSERT INTO TIME_ALLOCATION(time_id, enrollment_id)\n" +
                "VALUES(?,?)";
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(timeId));
            pst.setString(2, Integer.toString(enrollmentId));
            pst.executeUpdate();
            mysqlConnection.closeConnection();
            System.out.println("Enrollment Successful.....");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTimeRange(Time startTime, Time endTime, int dayOfWeek) {
        String sql = "INSERT INTO TIME_RANGE(start_time, end_time,day_of_week)\n" +
                "VALUES(?,?,?)";
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTime(1, startTime);
            pst.setTime(2, endTime);
            pst.setString(3, Integer.toString(dayOfWeek));
            pst.executeUpdate();
            mysqlConnection.closeConnection();
            System.out.println("New Time Range Added.....");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int findTimeId(Time startTime, Time endTime, int dayOfWeek) {
        String sql = "SELECT time_id FROM TIME_RANGE\n" +
                "WHERE ?=start_time AND ?=end_time\n" +
                "AND day_of_week=?";
        int timeId = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTime(1, startTime);
            pst.setTime(2, endTime);
            pst.setInt(3, dayOfWeek);
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                timeId = rs.getInt("time_id");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return timeId;
    }

    public boolean verifyFreeTime(Time startTime, Time endTime, int dayOfWeek) {
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
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                count = rs.getInt("count");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (count == 0) ? true : false;
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
            System.out.println("Query Executed......\n");
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
