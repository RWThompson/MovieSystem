package name.rwthompson.movie_system.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Helper class to handle common database tasks so that methods are not duplicated
 */
public class DatabaseHelper {

    private String dbURL = "jdbc:mysql://localhost:3306/movies";
    private String dbUsername = "root";
    private String dbPassword = "root";

    public boolean runUpdateQuery(String sqlStatement) throws SQLException {
        boolean isSuccessful = false;
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate(sqlStatement);
            isSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }

        return isSuccessful;
    }

    /**
     * Method to find the last used ID number (highest) in the specified table
     * @param table The table to find the last used ID for
     * @return A unique ID number for the specified table (last used + 1)
     * @throws SQLException
     */
    public int getUniqueID(String table) throws SQLException {
        int i = 0;
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs= null;
        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT MAX(id) FROM " + table + " WHERE id <> 0;");
            if(rs.next()) {
                i = rs.getInt("MAX(id)");
            }
            i++;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return i;
    }
}
