package net.therap.dao;

import net.therap.connector.MysqlDBMS;
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

    MysqlDBMS mysqlConnection;

    public TraineeDao()  {
        this.mysqlConnection = new MysqlDBMS();
    }

    public List<Trainee> getTraineesInfo(int courseId) {
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
}
