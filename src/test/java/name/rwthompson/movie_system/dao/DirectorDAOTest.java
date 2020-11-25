package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.Director;
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
public class DirectorDAOTest {

    @Mock
    private DirectorDAO mockedDirectorDAO;

    @Test
    public void testGetAllDirectors() throws SQLException {
        ArrayList<Director> directors = new ArrayList<>();
        directors.add(new Director(1, "Joe Russo"));
        directors.add(new Director(2, "Anthony Russo"));
        when(mockedDirectorDAO.getAllDirectors()).thenReturn(directors);
        assertEquals(directors, mockedDirectorDAO.getAllDirectors());
        Mockito.verify(mockedDirectorDAO).getAllDirectors();
    }

    @Test
    public void testGetDirectorByFullName() throws SQLException {
        Director director = new Director(1, "Joe Russo");
        when(mockedDirectorDAO.getDirectorByFullName("Joe Russo")).thenReturn(director);
        assertEquals(director, mockedDirectorDAO.getDirectorByFullName("Joe Russo"));
        Mockito.verify(mockedDirectorDAO).getDirectorByFullName("Joe Russo");
    }

    @Test
    public void testDirectorByID() throws SQLException {
        Director director = new Director(1, "Joe Russo");
        when(mockedDirectorDAO.getDirectorByID("1")).thenReturn(director);
        assertEquals(director, mockedDirectorDAO.getDirectorByID("1"));
        Mockito.verify(mockedDirectorDAO).getDirectorByID("1");
    }
}
