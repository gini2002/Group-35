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

    public Movie(String name) {
        this.name = name;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    // Add getters and setters for other properties.
}
