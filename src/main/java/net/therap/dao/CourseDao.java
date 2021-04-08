package net.therap.dao;

import net.therap.connector.MysqlDBMS;
import net.therap.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class CourseDao {

    MysqlDBMS mysqlConnection;

    public CourseDao() {
        this.mysqlConnection = new MysqlDBMS();
    }

    public Course getCourseInfo(int courseId) {
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
}
