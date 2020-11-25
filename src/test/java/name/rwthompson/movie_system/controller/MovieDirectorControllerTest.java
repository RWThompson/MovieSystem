package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.MovieDirectorDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.MovieDirector;
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
public class MovieDirectorControllerTest {

    @Mock
    private MovieDirectorDAO mockedMovieDirectorDAO;

    private DatabaseHelper databaseHelper;
    private MovieDirectorController movieDirectorController;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper();
        movieDirectorController = new MovieDirectorController();
    }

    @Test
    public void testCreateMovieDirector() throws SQLException {
        String returnString = "Successfully added the movieID of 3 to the database and attached it to the directorID "
                + "3 and id of " + databaseHelper.getUniqueID("Movie_Director");
        String result = movieDirectorController.createMovieDirector(3, 3);
        assertEquals(returnString, result);
    }

    @Test
    public void testUpdateDirectorIdInMovieDirector() throws SQLException {
        String returnString = "Successfully updated the directorID to 4 at id 3";
        String result = movieDirectorController.updateDirectorIDInMovieDirector(3, 4);
        assertEquals(returnString, result);
    }

    @Test
    public void testUpdateMovieIdInMovieDirector() throws SQLException {
        String returnString = "Successfully updated the movieID to 4 at id 3";
        String result = movieDirectorController.updateMovieIDInMovieDirector(3, 4);
        assertEquals(returnString, result);
    }

    @Test
    public void testDeleteMovieDirector() throws SQLException {
    int lastID = databaseHelper.getUniqueID("Movie_Director") - 1;
        String returnString = "Successfully deleted the Movie Director with ID "
                + lastID;
        String result = movieDirectorController.deleteMovieDirector(lastID);
        assertEquals(returnString, result);
    }

    @Test
    public void testGetAllMovieDirector() throws SQLException {
        ArrayList<MovieDirector> movieDirectors = new ArrayList<>();
        movieDirectors.add(new MovieDirector(1, 2, 3));
        movieDirectors.add(new MovieDirector(4, 5, 6));
        when(mockedMovieDirectorDAO.getAllMovieDirector()).thenReturn(movieDirectors);
        assertEquals(movieDirectors, mockedMovieDirectorDAO.getAllMovieDirector());
        Mockito.verify(mockedMovieDirectorDAO).getAllMovieDirector();
    }

    @Test
    public void testGetMovieDirectorById() throws SQLException {
        MovieDirector movieDirector = new MovieDirector(1, 1, 1);
        when(mockedMovieDirectorDAO.getMovieDirectorById("1")).thenReturn(movieDirector);
        assertEquals(movieDirector, mockedMovieDirectorDAO.getMovieDirectorById("1"));
        Mockito.verify(mockedMovieDirectorDAO).getMovieDirectorById("1");
    }
}
