package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private ArrayList<String> genre;
    private LocalDate releaseDate;
    private double rating;
    private String poster_path;
    private String overview;

    // Add other properties like genre, release date, etc.

    public Movie(String name, int id) {
        this.name = name;
        this.id = id;

        // Initialize other properties as needed.
    }

    public String getOverview(){return overview;}

    public void setOverview(String overview){this.overview = overview;}

    public String getPoster_path(){return poster_path;}

    public void setPoster_path(String poster_path){this.poster_path = poster_path;}


    public String getName() {
        return name;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public int getID() {return id;}

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }


    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }


    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    // Add getters and setters for other properties.
}
