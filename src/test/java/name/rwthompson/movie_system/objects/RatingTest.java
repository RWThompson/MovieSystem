package name.rwthompson.movie_system.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingTest {

    @Test
    public void testConstructor() {
        Rating rating = new Rating(1, 2, 3);
        assertEquals(1, rating.getID());
        assertEquals(2, rating.getRating());
        assertEquals(3, rating.getMovieID());
    }

    @Test
    public void testRatingOnlyConstructor() {
        Rating rating = new Rating(1);
        assertEquals(1, rating.getRating());
    }

    @Test
    public void testSettersAndGetters() {
        Rating rating = new Rating(1, 2, 3);
        rating.setID(4);
        rating.setRating(5);
        rating.setMovieID(6);
        assertEquals(4, rating.getID());
        assertEquals(5, rating.getRating());
        assertEquals(6, rating.getMovieID());
    }
}
