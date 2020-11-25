package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.MovieDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.Director;
import name.rwthompson.movie_system.objects.Movie;
import name.rwthompson.movie_system.objects.Rating;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    private MovieDAO movieDAO;
    private DatabaseHelper databaseHelper;
    private MovieController movieController;
    private MovieDAO mockedMovieDAO;

    @Before
    public void setUp() {
        mockedMovieDAO = mock(MovieDAO.class);
        databaseHelper = mock(DatabaseHelper.class);
        movieController = mock(MovieController.class);
        movieDAO = new MovieDAO();
        databaseHelper = new DatabaseHelper();
        movieController = new MovieController();
    }

    @Test
    public void testCreateMovie() throws SQLException {
        String returnString = "Successfully added Shooter to the database with id "
                + databaseHelper.getUniqueID("Movies");
        String result = movieController.createMovie("Shooter");
        assertEquals(returnString, result);
    }

    @Test
    public void testUpdateMovie() throws SQLException {
        String returnString = "Successfully updated the database at id 1 with the title Shooter";
        String result = movieController.updateMovie(1, "Shooter");
        assertEquals(returnString, result);
    }

    @Test
    public void testDeleteMovie() throws SQLException {
        int lastID = databaseHelper.getUniqueID("Movies") - 1;
        String returnString = "Successfully deleted the movie with ID " + lastID;
        String result = movieController.deleteMovie(lastID);
        assertEquals(returnString, result);
    }

    @Test
    public void testReadMovieByDirector() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Avengers Endgame"));
        movies.add(new Movie(6, "Captain America Civil War"));
        Director director = new Director(2, "Joe Russo");
        when(mockedMovieDAO.searchMovieByDirector(director)).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.searchMovieByDirector(director));
        Mockito.verify(mockedMovieDAO).searchMovieByDirector(director);
    }

    @Test
    public void testReadMovieAboveRating() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Avengers Endgame"));
        movies.add(new Movie(5, "Black Panther"));
        movies.add(new Movie(6, "Captain America Civil War"));
        Rating rating = new Rating(7);
        when(mockedMovieDAO.searchMoviesAboveRating(rating)).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.searchMoviesAboveRating(rating));
        Mockito.verify(mockedMovieDAO).searchMoviesAboveRating(rating);
    }

    @Test
    public void testReadAllMovies() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shooter"));
        movies.add(new Movie(2, "Bad Boys"));
        when(mockedMovieDAO.getAllMovies()).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.getAllMovies());
        Mockito.verify(mockedMovieDAO).getAllMovies();
    }

    @Test
    public void testReadMovieByTitle() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shooter"));
        when(mockedMovieDAO.getMoviesByTitle("Shooter")).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.getMoviesByTitle("Shooter"));
        Mockito.verify(mockedMovieDAO).getMoviesByTitle("Shooter");
    }

    @Test
    public void testReadMovieById() throws SQLException {
        Movie movie = new Movie(1, "Shooter");
        when(mockedMovieDAO.getMovieById("1")).thenReturn(movie);
        assertEquals(movie, mockedMovieDAO.getMovieById("1"));
        Mockito.verify(mockedMovieDAO).getMovieById("1");
    }
}
