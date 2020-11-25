package name.rwthompson.movie_system.controller;

import name.rwthompson.movie_system.dao.DirectorDAO;
import name.rwthompson.movie_system.helper.DatabaseHelper;
import name.rwthompson.movie_system.objects.Director;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class to map request URL's to the appropriate DAO method
 */
@RestController
@CrossOrigin("*")
public class DirectorController {

    /**
     * Method to get a unique ID from the Directors table then create a Movie object using that ID and the full name
     * provided and pass that to the DAO method to create the tuple
     * @param fullname The fullname of the director to be created
     * @return String returned from the DAO method, tells the user that the creation was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/create/director", method = {RequestMethod.POST, RequestMethod.GET})
    public String createDirector(@RequestParam(value = "fullname") String fullname) throws SQLException {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        int id = databaseHelper.getUniqueID("Directors");
        DirectorDAO directorDAO = new DirectorDAO();
        Director director = new Director(id, fullname);
        return directorDAO.createDirector(director);
    }

    /**
     * Method to find the director that the user wants to update using the id provided then changes the objects
     * full name and passes the updated object to the DAO to update the database
     * @param id The ID of the director to be updated
     * @param updatedName The new fullname for the movie
     * @return Message to tell the user that the update was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/update/director", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateDirector(@RequestParam(value = "id") int id,
                              @RequestParam(value = "updatedName") String updatedName) throws SQLException {
        DirectorDAO directorDAO = new DirectorDAO();
        Director director = directorDAO.getDirectorByID(String.valueOf(id));
        director.setFullname(updatedName);
        return directorDAO.updateDirector(director);
    }

    /**
     * Method to find the director that the user wants to delete then passes that object to the DAO to delete the
     * director from the database
     * @param id The ID of the director to be deleted
     * @return Message to tell the user that the deletion was successful
     * @throws SQLException
     */
    @RequestMapping(path = "/delete/director", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteDirector(@RequestParam(value = "id") int id) throws SQLException {
        DirectorDAO directorDAO = new DirectorDAO();
        Director director = directorDAO.getDirectorByID(String.valueOf(id));
        return directorDAO.deleteDirector(director);
    }

    /**
     * Method to call the DAO method that reads all directors from the database
     * @return ArrayList of all directors in the database
     * @throws SQLException
     */
    @RequestMapping(path = "/read/director/all", method = {RequestMethod.GET})
    public ArrayList<Director> readDirectors() throws SQLException {
        DirectorDAO directorDAO = new DirectorDAO();
        return directorDAO.getAllDirectors();
    }

    /**
     * Method to call the DAO method that reads directors by a specified directors full name in the database
     * @param fullname The full name of the director to look for in the database
     * @return ArrayList of directors with the specified full name
     * @throws SQLException
     */
    @RequestMapping(path = "/read/director/byname", method = {RequestMethod.GET})
    public Director readDirectorByName(@RequestParam(value = "fullname") String fullname) throws SQLException {
        DirectorDAO directorDAO = new DirectorDAO();
        return directorDAO.getDirectorByFullName(fullname);
    }

    /**
     * Method to call the DAO method that reads a specific director from the database by ID
     * @param id The ID of the director to search for
     * @return The director that the user search for
     * @throws SQLException
     */
    @RequestMapping(path = "/read/director/byid", method = {RequestMethod.GET})
    public Director readDirectorByID(@RequestParam(value = "id") String id) throws SQLException {
        DirectorDAO directorDAO = new DirectorDAO();
        return directorDAO.getDirectorByID(id);
    }
}
