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
    private String poster_path;
    private String overview;

    // Add other properties like genre, release date, etc.

    public Movie(String name) {
        this.name = name;
        // Initialize other properties as needed.
    }

    public String getOverview(){return overview;}

    public void setOverview(String overview){this.overview = overview;}

    public String getPoster_path(){return poster_path;}

    public void setPoster_path(String poster_path){this.poster_path = poster_path;}


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
