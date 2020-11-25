package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.MovieDirectorDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.MovieDirector;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class to handle CRUD functionality for the Movie_Director table
 */
@RestController
@CrossOrigin("*")
public class MovieDirectorController {

    /**
     * Create method to call the DAO create method, first gets a unique ID from the movie director table, then creates
     * a MovieDirector object using that ID, and the movieID and directorID provided by the user, then passes that
     * object to the DAO
     * @param movieID The movieID for the link
     * @param directorID The directorID for the link
     * @return Message to tell the user that the creation was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/create/moviedirector", method = {RequestMethod.POST, RequestMethod.GET})
    public String createMovieDirector(@RequestParam(value = "movieID") int movieID,
                               @RequestParam(value = "directorID") int directorID) throws SQLException {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        int id = databaseHelper.getUniqueID("Movie_Director");
        MovieDirectorDAO movieDirectorDAO = new MovieDirectorDAO();
        MovieDirector movieDirector = new MovieDirector(id, directorID, movieID);
        return movieDirectorDAO.createMovieDirector(movieDirector);
    }

    /**
     * Update method to call the DAO update method for updating the directoID in the link, first finds the movie
     * director link using the ID provided then changes the directorID in the returned object, then passes that object
     * to the update method in the DAO
     * @param id The ID of the movie director link to be updated
     * @param directorID The new directorID
     * @return Message to tell the user that the update was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/update/moviedirector/director", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateDirectorIDInMovieDirector(@RequestParam(value = "id") int id,
                               @RequestParam(value = "directorID") int directorID) throws SQLException {
        MovieDirectorDAO movieDirectorDAO = new MovieDirectorDAO();
        MovieDirector movieDirector = movieDirectorDAO.getMovieDirectorById(String.valueOf(id));
        movieDirector.setDirectorID(directorID);
        return movieDirectorDAO.updateMovieDirectorDirectorID(movieDirector);
    }

    /**
     * Update method to call the DAO update method for updating the movieID in the link, first finds the movie director
     * link using the ID provided then changes the movieID in the returned object, then passes that object to the
     * update method in the DAO
     * @param id The ID of the movie director link to be updated
     * @param movieID The new movieID
     * @return Message to tell the user that the update was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/update/moviedirector/movie", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateMovieIDInMovieDirector(@RequestParam(value = "id") int id,
                                                  @RequestParam(value = "movieID") int movieID) throws SQLException {
        MovieDirectorDAO movieDirectorDAO = new MovieDirectorDAO();
        MovieDirector movieDirector = movieDirectorDAO.getMovieDirectorById(String.valueOf(id));
        movieDirector.setMovieID(movieID);
        return movieDirectorDAO.updateMovieDirectorMovieID(movieDirector);
    }

    /**
     * Delete method to call the DAO delete method, first finds the movie director link of the ID provided in the
     * database, then passes that object to the DAO to be deleted
     * @param id The ID of the movie director link to be deleted
     * @return A message to tell the user that the deletion was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/delete/moviedirector", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteMovieDirector(@RequestParam(value = "id") int id) throws SQLException {
        MovieDirectorDAO movieDirectorDAO = new MovieDirectorDAO();
        MovieDirector movieDirector = movieDirectorDAO.getMovieDirectorById(String.valueOf(id));
        return movieDirectorDAO.deleteMovieDirector(movieDirector);
    }

    /**
     * Method to call the DAO method that searches all movie director links in the movie director bridge table
     * @return ArrayList of all movie director links
     * @throws SQLException
     */
    @RequestMapping(path = "/read/moviedirector/all", method = {RequestMethod.GET})
    public ArrayList<MovieDirector> readMovieDirector() throws SQLException {
        MovieDirectorDAO movieDirectorDAO = new MovieDirectorDAO();
        return movieDirectorDAO.getAllMovieDirector();
    }

    /**
     * Method to call the DAO method that searches the movie director table by ID
     * @param id The ID of the tuple to search for
     * @return The movie director link for the ID provided
     * @throws SQLException
     */
    @RequestMapping(path = "/read/moviedirector/byid", method = {RequestMethod.GET})
    public MovieDirector readMovieDirectorByID(@RequestParam(value = "id") String id) throws SQLException {
        MovieDirectorDAO movieDirectorDAO = new MovieDirectorDAO();
        return movieDirectorDAO.getMovieDirectorById(id);
    }
}
