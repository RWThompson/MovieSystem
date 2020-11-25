package name.rwthompson.movie_system.objects;

/**
 * Director class to create objects that relate to a row in the Director table
 */
public class Director {

    private int id;
    private String fullname;

    public Director(int id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
