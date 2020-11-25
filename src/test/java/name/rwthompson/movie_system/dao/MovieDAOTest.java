package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.Director;
import name.rwthompson.movie_system.objects.Movie;
import name.rwthompson.movie_system.objects.Rating;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieDAOTest {

    @Mock
    private MovieDAO mockedMovieDAO;

    private DriverManager mockedDriverManager;
    private Connection mockedConnection;
    private Statement mockedStatement;
    private ResultSet mockedResultSet;

    @Before
    public void setUp() {
        mockedDriverManager = mock(DriverManager.class);
        mockedConnection = mock(Connection.class);
        mockedStatement = mock(Statement.class);
        mockedResultSet = mock(ResultSet.class);
    }

    @Test
    public void testGetAllMoviesByDirector() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shooter"));
        movies.add(new Movie(2, "Bad Boys"));
        Director director = new Director(1, "John Smith");
        when(mockedMovieDAO.searchMovieByDirector(director)).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.searchMovieByDirector(director));
        Mockito.verify(mockedMovieDAO).searchMovieByDirector(director);
    }

    @Test
    public void testGetAllMoviesAboveRating() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shooter"));
        movies.add(new Movie(2, "Bad Boys"));
        Rating rating = new Rating(7);
        when(mockedMovieDAO.searchMoviesAboveRating(rating)).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.searchMoviesAboveRating(rating));
        Mockito.verify(mockedMovieDAO).searchMoviesAboveRating(rating);
    }

    @Test
    public void testGetAllMovies() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shooter"));
        movies.add(new Movie(2, "Bad Boys"));
        when(mockedMovieDAO.getAllMovies()).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.getAllMovies());
        Mockito.verify(mockedMovieDAO).getAllMovies();
    }

    @Test
    public void testGetMovieByTitle() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shooter"));
        when(mockedMovieDAO.getMoviesByTitle("Shooter")).thenReturn(movies);
        assertEquals(movies, mockedMovieDAO.getMoviesByTitle("Shooter"));
        Mockito.verify(mockedMovieDAO).getMoviesByTitle("Shooter");
    }

    @Test
    public void testGetMovieById() throws SQLException {
        Movie movie = new Movie(1, "Shooter");
        when(mockedMovieDAO.getMovieById("1")).thenReturn(movie);
        assertEquals(movie, mockedMovieDAO.getMovieById("1"));
        Mockito.verify(mockedMovieDAO).getMovieById("1");
    }
}
