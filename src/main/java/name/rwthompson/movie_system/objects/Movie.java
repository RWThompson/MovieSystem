package name.rwthompson.movie_system.objects;

/**
 * Movie class for creating objects that will relate to a row in the Movies table
 */
public class Movie {

    private int id;
    private String title;

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
