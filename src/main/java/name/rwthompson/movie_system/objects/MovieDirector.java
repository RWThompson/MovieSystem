package name.rwthompson.movie_system.objects;

/**
 * MovieDirector class to create objects that mirror a tuple in the Movie_Director table
 */
public class MovieDirector {

    private int id;
    private int directorID;
    private int movieID;

    public MovieDirector(int id, int directorID, int movieID) {
        this.id = id;
        this.directorID = directorID;
        this.movieID = movieID;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
