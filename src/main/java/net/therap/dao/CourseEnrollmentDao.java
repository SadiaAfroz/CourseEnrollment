package net.therap.dao;

import net.therap.connector.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseEnrollmentDao {

    private MysqlConnector mysqlConnection;

    public CourseEnrollmentDao() {
        this.mysqlConnection = new MysqlConnector();
    }

    public int findId(int courseId, int traineeId) {
        String sql = "SELECT enrollment_id from ENROLLMENT_PAIR where course_id=? AND trainee_id=?";
        int enrollmentId = -1;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, courseId);
            pst.setInt(2, traineeId);
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                enrollmentId = rs.getInt("enrollment_id");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return enrollmentId;
    }

    public void insert(int courseId, int traineeId) {
        String sql = "INSERT INTO ENROLLMENT_PAIR(course_id, trainee_id)\n" +
                "VALUES(?,?)";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, courseId);
            pst.setInt(2, traineeId);
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("New Enrollment Added.....");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(int courseId, int traineeId, int id) {
        String sqlTimeAllocation = "DELETE FROM TIME_ALLOCATION WHERE enrollment_id=?";
        String sqlEnrollment = "DELETE FROM ENROLLMENT_PAIR WHERE course_id=? AND trainee_id=?";
        Connection con = mysqlConnection.getConnection();
        try (PreparedStatement pstTimeAllocation = con.prepareStatement(sqlTimeAllocation);
             PreparedStatement pstEnrollment = con.prepareStatement(sqlEnrollment)) {

            con.setAutoCommit(false);
            pstTimeAllocation.setInt(1, id);
            pstTimeAllocation.executeUpdate();

            pstEnrollment.setInt(1, courseId);
            pstEnrollment.setInt(2, traineeId);
            pstEnrollment.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("Enrollment Deleted....");
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void updateTrainee(int oldTraineeId, int newTraineeId, int courseId) {
        String sql = "UPDATE ENROLLMENT_PAIR SET trainee_id=? WHERE trainee_id=? AND course_id=?";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, newTraineeId);
            pst.setInt(2, oldTraineeId);
            pst.setInt(3, courseId);
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("Trainee Updated.....");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public int isExist(int courseId, int traineeId) {
        String sql = "SELECT count(*) as count from ENROLLMENT_PAIR where course_id=? AND trainee_id=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, courseId);
            pst.setInt(2, traineeId);
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                count = rs.getInt("count");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
