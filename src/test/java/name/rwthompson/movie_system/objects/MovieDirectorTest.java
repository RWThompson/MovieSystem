package name.rwthompson.movie_system.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieDirectorTest {

    @Test
    public void testConstructor() {
        MovieDirector movieDirector = new MovieDirector(1, 2, 3);
        assertEquals(1, movieDirector.getID());
        assertEquals(2, movieDirector.getDirectorID());
        assertEquals(3, movieDirector.getMovieID());
    }

    @Test
    public void testSettersAndGetters() {
        MovieDirector movieDirector = new MovieDirector(1, 2, 3);
        movieDirector.setID(4);
        movieDirector.setDirectorID(5);
        movieDirector.setMovieID(6);
        assertEquals(4, movieDirector.getID());
        assertEquals(5, movieDirector.getDirectorID());
        assertEquals(6, movieDirector.getMovieID());
    }
}
