package net.therap.dao;

import net.therap.connector.MysqlConnector;
import net.therap.model.Trainee;

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
public class TraineeDao {

    private MysqlConnector mysqlConnection;

    public TraineeDao() {
        this.mysqlConnection = new MysqlConnector();
    }

    public List<Trainee> findAllByCourseId(int courseId) {
        List<Trainee> trainees = new ArrayList<>();
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
                trainee.setId(traineeId);
                trainee.setName(traineeName);
                trainees.add(trainee);
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trainees;
    }

    public int findByName(String name) {
        String sql = "SELECT trainee_id from TRAINEES where trainee_name=?";
        int traineeId = -1;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                traineeId = rs.getInt("trainee_id");
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return traineeId;
    }

    public int checkNameExist(String name) {
        String sql = "SELECT count(*) as count from TRAINEES where trainee_name=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
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

    public int checkIdExist(int id) {
        String sql = "SELECT count(*) as count from TRAINEES where trainee_id=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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

    public void insert(Trainee trainee) {
        String sql = "INSERT INTO TRAINEES(trainee_name)\n" +
                "VALUES(?)";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, trainee.getName());
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("New Trainee Added.....");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void update(Trainee trainee){
        String sql = "UPDATE TRAINEES SET trainee_name=? WHERE trainee_id=?";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, trainee.getName());
            pst.setInt(2, trainee.getId());
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("Trainee Name updated.....");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(Trainee trainee) {
        String sql =  "DELETE FROM TRAINEES WHERE trainee_id=?";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, trainee.getId());
            pst.executeUpdate();

            con.commit();
            mysqlConnection.closeConnection();
            System.out.println("Trainee deleted.....");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
