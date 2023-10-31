package entity;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String name;
    private String genre;
    private List<String> actors;
    private LocalDate releaseDate;
    private double rating;
    private String director;

    // Add other properties like genre, release date, etc.

    public Movie(String name, String genre, List<String> actors, LocalDate releaseDate, double rating, String director) {
        this.name = name;
        this.genre = genre;
        this.actors = actors;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.director = director;
        // Initialize other properties as needed.
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getActors() {
        return actors;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    // Add getters and setters for other properties.
}
