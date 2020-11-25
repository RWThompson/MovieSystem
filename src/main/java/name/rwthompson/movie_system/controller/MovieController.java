package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.DirectorDAO;
import name.rwthompson.movie_system.dao.MovieDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.Director;
import name.rwthompson.movie_system.objects.Movie;
import name.rwthompson.movie_system.objects.Rating;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class to map request URL's to the appropriate DAO method
 */
@RestController
@CrossOrigin("*")
public class MovieController {

    /**
     * Method to get a unique ID from the Movies table then create a Movie object using that ID and the title provided
     * and pass that to the DAO method to create the tuple
     * @param title The title of the movie to be created
     * @return String returned from the DAO method, tells the user that the creation was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/create/movie", method = {RequestMethod.POST, RequestMethod.GET})
    public String createMovie(@RequestParam(value = "title") String title) throws SQLException {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        int id = databaseHelper.getUniqueID("Movies");
        MovieDAO movieDAO = new MovieDAO();
        Movie movie = new Movie(id, title);
        return movieDAO.createMovie(movie);
    }

    /**
     * Method to find the movie that the user wants to update using the id provided then changes the objects title
     * and passes the updated object to the DAO to update the database
     * @param id The ID of the movie to be updated
     * @param updatedTitle The new title for the movie
     * @return Message to tell the user that the update was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/update/movie", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateMovie(@RequestParam(value = "id") int id,
                              @RequestParam(value = "updatedTitle") String updatedTitle) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        Movie movie = movieDAO.getMovieById(String.valueOf(id));
        movie.setTitle(updatedTitle);
        return movieDAO.updateMovie(movie);
    }

    /**
     * Method to find the movie that the user wants to delete then passes that object to the DAO to delete the movie
     * from the database
     * @param id The ID of the movie to be deleted
     * @return Message to tell the user that the deletion was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/delete/movie", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteMovie(@RequestParam(value = "id") int id) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        Movie movie = movieDAO.getMovieById(String.valueOf(id));
        return movieDAO.deleteMovie(movie);
    }

    /**
     * Method to read all movies by a specific director, it first finds the director in the database using the name
     * given by the user then passes that object to the DAO to find all movies that director has worked on
     * @param director The full name of the director that the user wants to find movies for
     * @return ArrayList of movies that the director has worked on
     * @throws SQLException
     */
    @RequestMapping(path = "/read/movie/bydirector", method = {RequestMethod.GET})
    public ArrayList<Movie> searchMovieByDirector(@RequestParam(value = "director") String director) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        DirectorDAO directorDAO = new DirectorDAO();
        Director directorObj = directorDAO.getDirectorByFullName(director);
        return movieDAO.searchMovieByDirector(directorObj);
    }

    /**
     * Method to read all movies with a higher average rating that the one specified by the user
     * @param rating The rating that the user wants to be the minimum
     * @return ArrayList of all movies with a higher average rating than the number provided
     * @throws SQLException
     */
    @RequestMapping(path = "/read/movie/aboverating", method = {RequestMethod.GET})
    public ArrayList<Movie> searchMoviesAboveRating(@RequestParam(value = "rating") int rating) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        Rating ratingObj = new Rating(rating);
        return movieDAO.searchMoviesAboveRating(ratingObj);
    }

    /**
     * Method to call the DAO method that reads all movies from the database
     * @return ArrayList of all movies in the database
     * @throws SQLException
     */
    @RequestMapping(path = "/read/movie/all", method = {RequestMethod.GET})
    public ArrayList<Movie> readMovies() throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        return movieDAO.getAllMovies();
    }

    /**
     * Method to call the DAO method that reads movies by a specified title in the database
     * @param title The title to look for in the database
     * @return ArrayList of movies with the specified title
     * @throws SQLException
     */
    @RequestMapping(path = "/read/movie/bytitle", method = {RequestMethod.GET})
    public ArrayList<Movie> readMovieByTitle(@RequestParam(value = "title") String title) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        return movieDAO.getMoviesByTitle(title);
    }

    /**
     * Method to call the DAO method that searches the database for a specific movie by ID
     * @param id The ID of the movie to search for
     * @return The movie that the user searched for
     * @throws SQLException
     */
    @RequestMapping(path = "/read/movie/byid", method = {RequestMethod.GET})
    public Movie readMovieByID(@RequestParam(value = "id") String id) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        return movieDAO.getMovieById(id);
    }
}
