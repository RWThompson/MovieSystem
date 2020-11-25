package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.Rating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class RatingsDAO {

    private String dbURL = "jdbc:mysql://localhost:3306/movies";
    private String dbUsername = "root";
    private String dbPassword = "root";

    public String createRating(Rating rating) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("INSERT INTO Ratings(id, rating, movieID) VALUES(" + rating.getID() + ", "
                    + rating.getRating() + ", " + rating.getMovieID() + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully added the rating of " + rating.getRating() + " to the database with id " + rating.getID()
                + " and attached it to the movieID " + rating.getMovieID();
    }

    public String updateRating(Rating rating) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("UPDATE Ratings SET rating = " + rating.getRating() + " WHERE id = "
                    + rating.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully updated the database at id " + rating.getID() + " with the rating " + rating.getRating();
    }

    public String deleteRating(Rating rating) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("DELETE FROM Ratings WHERE id = " + rating.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully deleted the Rating with ID " + rating.getID();
    }

    public ArrayList<Rating> getAllRatings() throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Rating> ratings = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Ratings;");
            while(rs.next()) {
//                ratings = ratings + "ID: " + rs.getString("id") + ", Rating: " + rs.getString("rating")
//                        + ", Movie ID: " + rs.getString("movieID") + "<br>";
                ratings.add(new Rating(parseInt(rs.getString("id")),
                        (parseInt(rs.getString("rating"))),
                        (parseInt(rs.getString("movieID")))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return ratings;
    }

    public ArrayList<Rating> getRatingsByMovieId(int movieID) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Rating> ratingList = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Ratings WHERE movieID = " + movieID + ";");
            while(rs.next()) {
//                rating = rating + "ID: " + rs.getString("id") + ", Rating: " + rs.getString("rating") +
//                        ", Movie ID: " + rs.getString("movieID") + "<br>";
                ratingList.add(new Rating(parseInt(rs.getString("id")),
                        (parseInt(rs.getString("rating"))),
                        (parseInt(rs.getString("movieID")))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return ratingList;
    }

    public Rating getRatingById(String id) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        Rating rating = new Rating(0, 0, 0);

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Ratings WHERE id = \"" + id + "\";");
            while(rs.next()) {
//                rating = "ID: " + rs.getString("id") + ", Rating: " + rs.getString("rating")
//                        + ", Movie ID:" + rs.getString("movieID");
                rating.setID(parseInt(rs.getString("id")));
                rating.setRating(parseInt(rs.getString("rating")));
                rating.setMovieID(parseInt(rs.getString("movieID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return rating;
    }
}
