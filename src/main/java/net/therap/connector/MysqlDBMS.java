package net.therap.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sadia.afroz
 * @since 3/30/21
 */
public class MysqlDBMS {

    private String userName;
    private String password;
    private String serverName;
    private int portNumber;
    private Connection connection;
    private static final String DBMS = "mysql";
    private static final String SCHEMA = "course_enrollment";

    public MysqlDBMS() {
        this.userName = "root";
        this.password = "Welcome123!";
        this.serverName = "localhost";
        this.portNumber = 3306;
        this.connection = null;
    }

    public MysqlDBMS(String userName, String password, String serverName, int portNumber) {
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.connection = null;
    }

    public Connection getConnection() {

        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("\nClass Loaded......\n");
            this.connection = DriverManager.getConnection(
                    "jdbc:" + DBMS + "://" +
                            this.serverName +
                            ":" + this.portNumber + "/" + SCHEMA,
                    connectionProps);
            System.out.println("Connected to database......\n");
        } catch (SQLException throwables) {
            System.out.println("Connection Failed! Check it from console......\n");
            throwables.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(MysqlDBMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection = null;
            }
        } catch (Exception e) {
            System.out.println("Connection is closed......\n");
            System.out.println(e);
        }
    }
}
