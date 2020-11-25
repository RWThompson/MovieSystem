package name.rwthompson.movie_system.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void testConstructor() {
        Movie movie = new Movie(1, "Batman Begins");
        assertEquals(1, movie.getID());
        assertEquals("Batman Begins", movie.getTitle());
    }

    @Test
    public void testSettersAndGetters() {
        Movie movie = new Movie(1, "Batman Begins");
        movie.setID(2);
        movie.setTitle("The Dark Knight");
        assertEquals(2, movie.getID());
        assertEquals("The Dark Knight", movie.getTitle());
    }
}
