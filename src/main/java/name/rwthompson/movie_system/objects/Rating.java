package name.rwthompson.movie_system.objects;

/**
 * Class to create objects that mirror the ratings table in the database
 */
public class Rating {

    private int id;
    private int rating;
    private int movieID;

    public Rating(int id, int rating, int movieID) {
        this.id = id;
        this.rating = rating;
        this.movieID = movieID;
    }

    public Rating(int rating) {
        this.rating = rating;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
