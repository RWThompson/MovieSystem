package name.rwthompson.movie_system.dao;

import name.rwthompson.movie_system.objects.MovieDirector;
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
public class MovieDirectorDAOTest {

    @Mock
    private MovieDirectorDAO mockedMovieDirectorDAO;

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
