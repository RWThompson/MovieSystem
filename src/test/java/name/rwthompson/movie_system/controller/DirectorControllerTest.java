package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.DirectorDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.Director;
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
public class DirectorControllerTest {

    @Mock
    private DirectorDAO mockedDirectorDAO;

    private DatabaseHelper databaseHelper;
    private DirectorController directorController;
    private DirectorDAO directorDAO;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper();
        directorController = new DirectorController();
    }

    @Test
    public void testCreateDirector() throws SQLException {
        String returnString = "Successfully added John Smith to the database with id "
                + databaseHelper.getUniqueID("Directors");
        String result = directorController.createDirector("John Smith");
        assertEquals(returnString, result);
    }

    @Test
    public void testUpdateDirector() throws SQLException {
        String returnString = "Successfully updated the database at id 1 with the name John Smith";
        String result = directorController.updateDirector(1, "John Smith");
        assertEquals(returnString, result);
    }

    @Test
    public void testDeleteDirector() throws SQLException {
        int lastID = databaseHelper.getUniqueID("Directors") - 1;
        String returnString = "Successfully deleted the director with ID " + lastID;
        String result = directorController.deleteDirector(lastID);
        assertEquals(returnString, result);
    }

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
