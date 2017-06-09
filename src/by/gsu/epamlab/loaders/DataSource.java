package by.gsu.epamlab.loaders;

import java.sql.*;

/**
 * Created by Home on 07.06.2017.
 */
public class DataSource {
    private static DataSource instance;

    private DataSource() {
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection ()  {
        String dbUrl = "jdbc:mysql://localhost/results";
        String user = "root";
        String password = "root";
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            System.err.println("Problems with connection: "  + e);
        }
        return con;
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("ResulSet closing problem : " + e);
            }
        }

    }
    public static void closeStatement(Statement ...statements){
        if(statements != null) {
            try {
                for(Statement st: statements){
                    st.close();
                }
            } catch (SQLException e) {
                System.err.println("Statement closing problem : " + e);
            }
        }

    }
    public static void closeConnection(Connection connection){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection closing problem : " + e);
            }
        }

    }
}
