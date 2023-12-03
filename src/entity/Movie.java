package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Movie class represents a movie entity with various properties such as name, genre, release date, rating, etc.
 */
public class Movie {
    private int id;
    private String name;
    private ArrayList<String> genre;
    private LocalDate releaseDate;
    private double rating;
    private String poster_path;
    private String overview;

    /**
     * Constructs a Movie object with the given name.
     *
     * @param name The name of the movie.
     */
    public Movie(String name) {
        this.name = name;
    }

    /**
     * Constructs a Movie object with the given name and ID.
     *
     * @param name The name of the movie.
     * @param id   The ID of the movie.
     */
    public Movie(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Constructs a Movie object with the specified properties.
     *
     * @param name        The name of the movie.
     * @param id          The ID of the movie.
     * @param genre       The genre(s) of the movie.
     * @param releaseDate The release date of the movie.
     * @param rating      The rating of the movie.
     * @param poster_path The path to the movie's poster.
     * @param overview    A brief overview or description of the movie.
     */
    public Movie(String name, int id, ArrayList<String> genre,
                 LocalDate releaseDate, double rating, String poster_path, String overview) {
        this.name = name;
        this.id = id;
        this.genre = genre;
        this.poster_path = poster_path;
        this.overview = overview;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    /**
     * Retrieves the overview of the movie.
     *
     * @return The overview of the movie.
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Retrieves the poster path of the movie.
     *
     * @return The poster path of the movie.
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     * Retrieves the name of the movie.
     *
     * @return The name of the movie.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the genre(s) of the movie.
     *
     * @return The genre(s) of the movie.
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * Retrieves the ID of the movie.
     *
     * @return The ID of the movie.
     */
    public int getID() {
        return id;
    }

    /**
     * Retrieves the release date of the movie.
     *
     * @return The release date of the movie.
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Retrieves the rating of the movie.
     *
     * @return The rating of the movie.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Returns a MovieBuilder for creating a Movie instance with a builder pattern.
     *
     * @return A MovieBuilder instance.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    /**
     * The MovieBuilder class facilitates the construction of Movie instances using the builder pattern.
     */
    public static class MovieBuilder {
        private int id;
        private String name;
        private ArrayList<String> genre;
        private LocalDate releaseDate;
        private double rating;
        private String poster_path;
        private String overview;

        MovieBuilder() {
        }

        /**
         * Sets the ID of the movie.
         *
         * @param id The ID of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder id(int id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the name of the movie.
         *
         * @param name The name of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the genre(s) of the movie.
         *
         * @param genre The genre(s) of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder genre(ArrayList<String> genre) {
            this.genre = genre;
            return this;
        }

        /**
         * Sets the release date of the movie.
         *
         * @param releaseDate The release date of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        /**
         * Sets the rating of the movie.
         *
         * @param rating The rating of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder rating(double rating) {
            this.rating = rating;
            return this;
        }

        /**
         * Sets the poster path of the movie.
         *
         * @param poster_path The poster path of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder poster_path(String poster_path) {
            this.poster_path = poster_path;
            return this;
        }

        /**
         * Sets the overview of the movie.
         *
         * @param overview The overview of the movie.
         * @return The MovieBuilder instance.
         */
        public MovieBuilder overview(String overview) {
            this.overview = overview;
            return this;
        }

        /**
         * Builds and returns a Movie instance with the specified properties.
         *
         * @return The built Movie instance.
         */
        public Movie build() {
            return new Movie(name, id, genre, releaseDate, rating, poster_path, overview);
        }
    }
}
