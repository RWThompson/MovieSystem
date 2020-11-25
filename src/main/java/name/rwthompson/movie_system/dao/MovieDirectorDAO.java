package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.MovieDirector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * DAO class to handle CRUD functionality for the Movie_Director bridge table
 */
public class MovieDirectorDAO {

    private String dbURL = "jdbc:mysql://localhost:3306/movies";
    private String dbUsername = "root";
    private String dbPassword = "root";

    /**
     * Create method to create a new movie director link
     * @param movieDirector The movie director link to be added
     * @return A message to tell the user that the creation was successful
     * @throws SQLException
     */
    public String createMovieDirector(MovieDirector movieDirector) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("INSERT INTO Movie_Director(id, movieID, directorID) VALUES(" + movieDirector.getID()
                    + ", " + movieDirector.getMovieID() + ", " + movieDirector.getDirectorID() +");");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }

        return "Successfully added the movieID of " + movieDirector.getMovieID()
                + " to the database and attached it to the directorID " + movieDirector.getDirectorID()
                + " and id of " + movieDirector.getID();
    }

    /**
     * Update method to update the director ID in a movie director link
     * @param movieDirector The movie director link to be updated (has the ID of the tuple and the new director ID)
     * @return A message to tell the user that the update was successful
     * @throws SQLException
     */
    public String updateMovieDirectorDirectorID(MovieDirector movieDirector) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("UPDATE Movie_Director SET directorID = " + movieDirector.getDirectorID()
                    + " WHERE id = " + movieDirector.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully updated the directorID to " + movieDirector.getDirectorID() + " at id " + movieDirector.getID();
    }

    /**
     * Update method to update the movie ID in a movie director link
     * @param movieDirector The movie director link to be updated (has the ID of the tuple and the new movie ID)
     * @return A message to tell the user that the update was successful
     * @throws SQLException
     */
    public String updateMovieDirectorMovieID(MovieDirector movieDirector) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("UPDATE Movie_Director SET movieID = " + movieDirector.getMovieID()
                    + " WHERE id = " + movieDirector.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully updated the movieID to " + movieDirector.getMovieID() + " at id " + movieDirector.getID();
    }

    /**
     * Delete method to remove a movie director link from the database
     * @param movieDirector The movie director link to be removed
     * @return A message to tell the user that the deletion was successful
     * @throws SQLException
     */
    public String deleteMovieDirector(MovieDirector movieDirector) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("DELETE FROM Movie_Director WHERE id = " + movieDirector.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully deleted the Movie Director with ID " + movieDirector.getID();
    }

    /**
     * Method to read all movie director links from the database
     * @return ArrayList of all movie director links
     * @throws SQLException
     */
    public ArrayList<MovieDirector> getAllMovieDirector() throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<MovieDirector> movieDirector = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Movie_Director;");
            while(rs.next()) {
//                movieDirector = movieDirector + "ID: " + rs.getString("id") + ", Movie ID: "
//                        + rs.getString("movieID")
//                        + ", Director ID: " + rs.getString("directorID") + "<br>";
                movieDirector.add(new MovieDirector(
                        parseInt(rs.getString("id")),
                        parseInt(rs.getString("directorID")),
                        parseInt(rs.getString("movieID"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movieDirector;
    }

    /**
     * Method to read a movie director link by ID
     * @param id The ID of the link to be searched
     * @return The movie director link specified by the user
     * @throws SQLException
     */
    public MovieDirector getMovieDirectorById(String id) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        MovieDirector movieDirector = new MovieDirector(0, 0, 0);

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Movie_Director WHERE id = \"" + id + "\";");
            while(rs.next()) {
//                movieDirector = "ID: " + rs.getString("id") + ", Movie ID: "
//                        + rs.getString("movieID")
//                        + ", Director ID: " + rs.getString("directorID");
                movieDirector.setID(parseInt(rs.getString("id")));
                movieDirector.setMovieID(parseInt(rs.getString("movieID")));
                movieDirector.setDirectorID(parseInt(rs.getString("directorID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movieDirector;
    }
}
