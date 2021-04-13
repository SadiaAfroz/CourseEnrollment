package net.therap.dao;

import net.therap.connector.MysqlConnector;
import net.therap.model.Course;

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
public class CourseDao {

    private MysqlConnector mysqlConnection;

    public CourseDao() {
        this.mysqlConnection = new MysqlConnector();
    }

    public Course findById(int courseId) {
        String sql = "SELECT course_name FROM COURSES WHERE course_id= ?";
        Course course = new Course();
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(courseId));
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {
                String courseName = rs.getString("course_name");
                course.setId(courseId);
                course.setName(courseName);
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return course;
    }

    public List<Course> findAllByTraineeId(int traineeId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT COURSES.course_id, COURSES.course_name FROM TRAINEES \n" +
                "JOIN ENROLLMENT_PAIR  ON TRAINEES.trainee_id=ENROLLMENT_PAIR.trainee_id\n" +
                "JOIN COURSES ON COURSES.course_id=ENROLLMENT_PAIR.course_id \n" +
                "WHERE TRAINEES.trainee_id=?";
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(traineeId));
            ResultSet rs = pst.executeQuery();
            System.out.println("Query Executed......\n");
            while (rs.next()) {

                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                Course course = new Course(courseId, courseName);
                courses.add(course);
            }
            mysqlConnection.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courses;
    }

    public int checkNameExist(String courseName) {
        String sql = "SELECT count(*) as count from COURSES where course_name=?";
        int count = 0;
        try {
            Connection con = mysqlConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, courseName);
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
        String sql = "SELECT count(*) as count from COURSES where course_id=?";
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

    public void insert(Course course) {
        String sql = "INSERT INTO COURSES(course_name)\n" +
                "VALUES(?)";
        Connection con = mysqlConnection.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, course.getName());
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
}
