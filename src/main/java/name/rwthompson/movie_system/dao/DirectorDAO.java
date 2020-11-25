package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.Director;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * DAO class to handle all CRUD functionality for the Director table
 */
public class DirectorDAO {

    private String dbURL = "jdbc:mysql://localhost:3306/movies";
    private String dbUsername = "root";
    private String dbPassword = "root";

    /**
     * Method to connect to the database and create a new director
     * @param director The director to be added to the database
     * @return A message to tell the user that the creation was successful
     * @throws SQLException
     */
    public String createDirector(Director director) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("INSERT INTO Directors(id, fullname) VALUES(" + director.getID()
                    + ", \"" + director.getFullname() + "\");");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }

        return "Successfully added " + director.getFullname() + " to the database with id " + director.getID();
    }

    /**
     * Update method to change the name of a director in the database
     * @param director The director to be updated (has the ID of the tuple to be updated and the new name)
     * @return A message to tell the user that the update was successful
     * @throws SQLException
     */
    public String updateDirector(Director director) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("UPDATE Directors SET fullname = \"" + director.getFullname()
                    + "\" WHERE id = " + director.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully updated the database at id " + director.getID() + " with the name " + director.getFullname();
    }

    /**
     * Delete method to remove a director from the database by ID
     * @param director The director to be removed
     * @return A message to tell the user that the deletion was successful
     * @throws SQLException
     */
    public String deleteDirector(Director director) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("DELETE FROM Directors WHERE id = " + director.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully deleted the director with ID " + director.getID();
    }

    /**
     * Method to read all directors from the database
     * @return ArrayList of all directors in the database
     * @throws SQLException
     */
    public ArrayList<Director> getAllDirectors() throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Director> directors = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Directors;");
            while(rs.next()) {
//                directors = directors + "ID: " + rs.getString("id") + ", Full Name: "
//                        + rs.getString("fullname") + "<br>";
                directors.add(new Director(parseInt(rs.getString("id")), rs.getString("fullname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return directors;
    }

    /**
     * Method to search the database for director(s) by full name
     * @param fullname The full name of the director(s) to be searched for
     * @return The director with the specified name
     * @throws SQLException
     */
    public Director getDirectorByFullName(String fullname) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        Director director = new Director(0, "");

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Directors WHERE fullname = \"" + fullname + "\";");
            while(rs.next()) {
//                director = "ID: " + rs.getString("id") + ", Full Name: " + rs.getString("fullname");
                director.setID(parseInt(rs.getString("id")));
                director.setFullname(rs.getString("fullname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return director;
    }

    /**
     * Method to search for a specific director by ID
     * @param id The ID of the director to search for
     * @return The director with the specified ID
     * @throws SQLException
     */
    public Director getDirectorByID(String id) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        Director director = new Director(0, null);

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Directors WHERE id = \"" + id + "\";");
            while(rs.next()) {
//                director = "ID: " + rs.getString("id") + ", Full Name: " + rs.getString("fullname");
                director.setID(parseInt(rs.getString("id")));
                director.setFullname(rs.getString("fullname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return director;
    }
}
