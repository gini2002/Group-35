package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a watchlist containing a list of movies.
 * This class provides functionality to manage a collection of Movie objects,
 * such as adding or removing movies from the watchlist.
 */

    public class Watchlist {
        private List<Movie> list = new ArrayList<>();

        /**
        * Constructs an empty Watchlist.
        */

        public Watchlist() {
        }

        /**
        * Constructs a Watchlist with a predefined list of movies.
        *
        * @param movies A list of Movie objects to be initially added to the watchlist.
        */

        public Watchlist(List<Movie> movies) {
            this.list = movies;
        }

        /**
        * Adds a Movie object to the watchlist.
        *
        * @param movie The Movie object to add.
        */

        public void addMovie(Movie movie) {
            list.add(movie);
        }

        /**
        * Removes a Movie object from the watchlist.
        * If the movie is not in the watchlist, the method has no effect.
        *
        * @param movie The Movie object to remove.
        */

        public void deleteMovie(Movie movie) {
            list.remove(movie);
        }

        /**
         * Retrieves the list of Movie objects in the watchlist.
        *
        * @return A list of Movie objects currently in the watchlist.
        */

        public List<Movie> getWatchlist() {
            return list;
        }

        /**
        * Converts the watchlist to a string representation.
        * The string consists of movie IDs separated by '#' character.
        * If the watchlist is empty, returns an empty string.
        *
        * @return A string representation of the watchlist.
        */

        @Override
        public String toString() {
            String result = "";
            if (list == null) {
                            return "";
            } else if (list.isEmpty()) {
                return "";
            } else {
                for (Movie movie : list) {
                    int id = movie.getID();
                    result = result + id + "#";
                }
            }
            return result.substring(0, result.length()-1);
        }
    }

