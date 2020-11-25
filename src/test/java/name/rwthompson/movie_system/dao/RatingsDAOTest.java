package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.Rating;
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
public class RatingsDAOTest {

    @Mock
    private RatingsDAO mockedRatingsDAO;

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
