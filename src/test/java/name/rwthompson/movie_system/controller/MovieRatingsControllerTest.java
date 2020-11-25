package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.RatingsDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.Rating;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieRatingsControllerTest {

    @Mock
    private RatingsDAO mockedRatingsDAO;

    private DatabaseHelper databaseHelper;
    private MovieRatingsController movieRatingsController;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper();
        movieRatingsController = new MovieRatingsController();
    }

    @Test
    public void testcreateRating() throws SQLException {
        String returnString = "Successfully added the rating of 5 to the database with id "
                + databaseHelper.getUniqueID("Ratings") + " and attached it to the movieID 3";
        String result = movieRatingsController.createRating(5, 3);
        assertEquals(returnString, result);
    }

    @Test
    public void testUpdateRating() throws SQLException {
        String returnString = "Successfully updated the database at id 13 with the rating 4";
        String result = movieRatingsController.updateRating(13, 4);
        assertEquals(returnString, result);
    }

    @Test
    public void testDeleteRating() throws SQLException {
        int lastID = databaseHelper.getUniqueID("Ratings") - 1;
        String returnString = "Successfully deleted the Rating with ID " + lastID;
        String result = movieRatingsController.deleteRating(lastID);
        assertEquals(returnString, result);
    }

    @Test
    public void testGetAllRatings() throws SQLException {
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(1, 2, 3));
        ratings.add(new Rating(4, 5, 6));
        when(mockedRatingsDAO.getAllRatings()).thenReturn(ratings);
        assertEquals(ratings, mockedRatingsDAO.getAllRatings());
        Mockito.verify(mockedRatingsDAO).getAllRatings();
    }

    @Test
    public void testGetRatingsByMovieId() throws SQLException {
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(1, 2, 3));
        when(mockedRatingsDAO.getRatingsByMovieId(3)).thenReturn(ratings);
        assertEquals(ratings, mockedRatingsDAO.getRatingsByMovieId(3));
        Mockito.verify(mockedRatingsDAO).getRatingsByMovieId(3);
    }

    @Test
    public void testGetRatingById() throws SQLException {
        Rating rating = new Rating(1, 2, 3);
        when(mockedRatingsDAO.getRatingById("1")).thenReturn(rating);
        assertEquals(rating, mockedRatingsDAO.getRatingById("1"));
        Mockito.verify(mockedRatingsDAO).getRatingById("1");
    }
}
