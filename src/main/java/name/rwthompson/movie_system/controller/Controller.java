package name.rwthompson.movie_system.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for a front page so that just navigating to localhost:8080
 * doesn't produce an error page
 */
@RestController
@CrossOrigin("*")
public class Controller {

    /**
     * Method to display all request mappings on the home page
     * @return String of all request mappings
     */
    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public String hello() {
        return String.format("| /create/movie | title |<br>" +
                "| /update/movie | id, updatedTitle |<br>" +
                "| /delete/movie | id |<br>" +
                "| /read/movie/bydirector | director |<br>" +
                "| /read/movie/aboverating | rating |<br>" +
                "| /read/movie/all |  |<br>" +
                "| /read/movie/bytitle | title |<br>" +
                "| /read/movie/byid | id |<br>" +
                "| /create/director | fullname |<br>" +
                "| /update/director | id, updatedName |<br>" +
                "| /delete/director | id |<br>" +
                "| /read/director/all |  |<br>" +
                "| /read/director/byname | fullname |<br>" +
                "| /read/director/byid | id |<br>" +
                "| /create/rating | rating, movieID |<br>" +
                "| /update/rating | id, updatedRating |<br>" +
                "| /delete/rating | id |<br>" +
                "| /read/rating/all |  |<br>" +
                "| /read/rating/bymovieID | movieID |<br>" +
                "| /read/rating/byid | id |<br>" +
                "| /create/moviedirector | movieID, directorID |<br>" +
                "| /update/moviedirector/director | id, directorID |<br>" +
                "| /update/moviedirector/movie | id, movieID |<br>" +
                "| /delete/moviedirector | id |<br>" +
                "| /read/moviedirector/all |  |<br>" +
                "| /read/moviedirector/byid | id |");
    }
}
