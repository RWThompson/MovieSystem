package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.Director;
import name.rwthompson.movie_system.objects.Movie;
import name.rwthompson.movie_system.objects.Rating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * DAO class to handle CRUD functionality for the movies table in the database
 */
public class MovieDAO {

    private String dbURL = "jdbc:mysql://localhost:3306/movies";
    private String dbUsername = "root";
    private String dbPassword = "root";

    /**
     * Create method to add a new movie into the database with the provided movie object
     * @param movie The movie to be added
     * @return A message to tell the user that the creation was successful
     * @throws SQLException
     */
    public String createMovie(Movie movie) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("INSERT INTO Movies(id, title) VALUES(" + movie.getID() + ", \"" + movie.getTitle() + "\");");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully added " + movie.getTitle() + " to the database with id " + movie.getID();
    }

    /**
     * Update method to change the title of a movie in the database
     * @param movie The movie to be updated (has the ID of the movie to be updated and the new title)
     * @return Message to tell the user that the update was successful
     * @throws SQLException
     */
    public String updateMovie(Movie movie) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("UPDATE Movies SET title = \"" + movie.getTitle() + "\" WHERE id = " + movie.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully updated the database at id " + movie.getID() + " with the title " + movie.getTitle();
    }

    /**
     * Delete method to remove a movie from the database
     * @param movie The movie to be removed
     * @return Message to tell the user that the deletion was successful
     * @throws SQLException
     */
    public String deleteMovie(Movie movie) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            stmnt.executeUpdate("DELETE FROM Movies WHERE id = " + movie.getID() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
        }
        return "Successfully deleted the movie with ID " + movie.getID();
    }

    /**
     * Method to search the database for all movies by a specified director
     * @param director The director to search their movies for
     * @return ArrayList of all movies by the specified director
     * @throws SQLException
     */
    public ArrayList<Movie> searchMovieByDirector(Director director) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT Movies.title as \"Title\", Movies.id as \"ID\" " +
                    "FROM Movies, Directors, Movie_Director " +
                    "WHERE\n" + "Directors.fullname = \"" + director.getFullname() + "\" " +
                    "AND Directors.id = Movie_Director.directorID " +
                    "AND Movie_Director.movieID = Movies.id;");
            while(rs.next()) {
//                movies = movies + "Title: " + rs.getString("Title") + ", Director: "
//                        + rs.getString("Director") + "<br>";
                Movie movie = new Movie(parseInt(rs.getString("ID")), rs.getString("Title"));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movieList;
    }

    /**
     * Method to search for all movies with a higher average rating than the rating specified
     * @param rating The minimum rating to find movies higher than
     * @return ArrayList of all movies with a higher average rating than the one specified
     * @throws SQLException
     */
    public ArrayList<Movie> searchMoviesAboveRating(Rating rating) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT Movies.title as \"Title\", Movies.id as ID \n" +
                    "FROM Ratings, Movies " +
                    "WHERE Ratings.movieID = Movies.id " +
                    "GROUP BY Ratings.movieID " +
                    "HAVING AVG(Ratings.rating)>" + rating.getRating() + ";");
            while(rs.next()) {
//                movies = movies + "Title: " + rs.getString("Title") + ", Rating: "
//                        + rs.getString("Rating") + "<br>";
                movieList.add(new Movie(parseInt(rs.getString("ID")), rs.getString("Title")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movieList;
    }

    /**
     * Method to find all movies in the database
     * @return ArrayList of all movies in the database
     * @throws SQLException
     */
    public ArrayList<Movie> getAllMovies() throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Movies;");
            while(rs.next()) {
//                movies = movies + "ID: " + rs.getString("id") + ", Title: "
//                        + rs.getString("title") + "<br>";
                movies.add(new Movie(parseInt(rs.getString("id")), rs.getString("title")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movies;
    }

    /**
     * Method to search the database for movies with a specified title
     * @param title The title to search the database for
     * @return ArrayList of all movies with the specified title
     * @throws SQLException
     */
    public ArrayList<Movie> getMoviesByTitle(String title) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Movies WHERE title = \"" + title + "\";");
            while(rs.next()) {
//                movie = "ID: " + rs.getString("id") + ", Title: " + rs.getString("title");
                movieList.add(new Movie(parseInt(rs.getString("id")), rs.getString("title")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movieList;
    }

    /**
     * Method to search the database for a specific movie by ID
     * @param id The ID of the movie to search the database for
     * @return The movie attached to the specified title
     * @throws SQLException
     */
    public Movie getMovieById(String id) throws SQLException {
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;
        Movie movie = new Movie(0, null);

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM Movies WHERE id = \"" + id + "\";");
            while(rs.next()) {
//                movie = "ID: " + rs.getString("id") + ", Title: " + rs.getString("title");
                movie.setID(parseInt(rs.getString("id")));
                movie.setTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmnt.close();
            rs.close();
        }
        return movie;
    }
}
