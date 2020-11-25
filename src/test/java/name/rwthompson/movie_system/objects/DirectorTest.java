package name.rwthompson.movie_system.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectorTest {

    @Test
    public void testConstructor() {
        Director director = new Director(1, "Joe Russo");
        assertEquals(1, director.getID());
        assertEquals("Joe Russo", director.getFullname());
    }

    @Test
    public void testSettersAndGetters() {
        Director director = new Director(1, "Joe Russo");
        director.setID(2);
        director.setFullname("Anthony Russo");
        assertEquals(2, director.getID());
        assertEquals("Anthony Russo", director.getFullname());
    }
}
