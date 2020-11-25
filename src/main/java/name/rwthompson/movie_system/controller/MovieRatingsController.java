package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.RatingsDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.Rating;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class to handle CRUD functionality for the Ratings table
 */
@RestController
@CrossOrigin("*")
public class MovieRatingsController {

    /**
     * Create method to call the DAO method that creates a new tuple in the ratings table, by first getting an unique
     * ID number from the database, then creating an object with that ID, and the rating and movieID given by the user
     * then passing that object to the DAO to create the rating
     * @param rating The rating of the film
     * @param movieID The ID of the movie to attach the rating to
     * @return A message to tell the user that the creation was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/create/rating", method = {RequestMethod.POST, RequestMethod.GET})
    public String createRating(@RequestParam(value = "rating") int rating,
                                 @RequestParam(value = "movieID") int movieID) throws SQLException {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        int id = databaseHelper.getUniqueID("Ratings");
        RatingsDAO ratingsDAO = new RatingsDAO();
        Rating ratingObj = new Rating(id, rating, movieID);
        return ratingsDAO.createRating(ratingObj);
    }

    /**
     * Update method to update a rating by ID by first finding the specified rating in the database (by ID) then
     * setting the rating in the object to the new rating, then passing that object to the update DAO method to update
     * the tuple in the database
     * @param id The ID of the rating to be updated
     * @param updatedRating The new rating
     * @return Message to tell the user that the update was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/update/rating", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateRating(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "updatedRating") int updatedRating) throws SQLException {
        RatingsDAO ratingsDAO = new RatingsDAO();
        Rating rating = ratingsDAO.getRatingById(String.valueOf(id));
        rating.setRating(updatedRating);
        return ratingsDAO.updateRating(rating);
    }

    /**
     * Delete a rating in the database by ID by first finding the rating by ID then passing that rating to the
     * DAO delete method to remove it from the database
     * @param id The ID of the rating to be deleted
     * @return Message to tell the user that the deletion was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/delete/rating", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteRating(@RequestParam(value = "id") int id) throws SQLException {
        RatingsDAO ratingsDAO = new RatingsDAO();
        Rating rating = ratingsDAO.getRatingById(String.valueOf(id));
        return ratingsDAO.deleteRating(rating);
    }

    /**
     * Method to call the DAO method that reads all ratings from the database
     * @return ArrayList of all ratings in the database
     * @throws SQLException
     */
    @RequestMapping(path = "/read/rating/all", method = {RequestMethod.GET})
    public ArrayList<Rating> readRatings() throws SQLException {
        RatingsDAO ratingsDAO = new RatingsDAO();
        return ratingsDAO.getAllRatings();
    }

    /**
     * Method to call the DAO method that searches ratings based on the movieID
     * @param movieID The movieID to find ratings for
     * @return ArrayList of all ratings that are attached to the specified movieID
     * @throws SQLException
     */
    @RequestMapping(path = "/read/rating/bymovieID", method = {RequestMethod.GET})
    public ArrayList<Rating> readRatingByMovieID(@RequestParam(value = "movieID") int movieID) throws SQLException {
        RatingsDAO ratingsDAO = new RatingsDAO();
        return ratingsDAO.getRatingsByMovieId(movieID);
    }

    /**
     * Method to call the DAO method that searches the Ratings table for a specific rating by ID
     * @param id The ID of the rating to search for
     * @return The Rating that the user searched for
     * @throws SQLException
     */
    @RequestMapping(path = "/read/rating/byid", method = {RequestMethod.GET})
    public Rating readRatingByID(@RequestParam(value = "id") String id) throws SQLException {
        RatingsDAO ratingsDAO = new RatingsDAO();
        return ratingsDAO.getRatingById(String.valueOf(id));
    }
}
