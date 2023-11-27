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
    public Movie(String name, int id){
        this.name = name;
        this.id = id;
    }

    public Movie(String name, int id, ArrayList<String> genre,
                 LocalDate releaseDate, double rating, String poster_path, String overview) {
        this.name = name;
        this.id = id;
        this.genre = genre;
        this.poster_path = poster_path;
        this.overview = overview;
        this.rating = rating;
        this.releaseDate = releaseDate;
        // Initialize other properties as needed.
    }

    public String getOverview(){return overview;}

    public String getPoster_path(){return poster_path;}

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

    public  static MovieBuilder builder() {
        return new MovieBuilder();
    }

    // Add getters and setters for other properties.
    public static class MovieBuilder {
        private int id;
        private String name;
        private ArrayList<String> genre;
        private LocalDate releaseDate;
        private double rating;
        private String poster_path;
        private String overview;

        MovieBuilder(){}
        public MovieBuilder id(int id){
            this.id = id;
            return this;
        }
        public MovieBuilder name(String name){
            this.name = name;
            return this;
        }

        public MovieBuilder genre(ArrayList<String> genre){
            this.genre = genre;
            return this;
        }
        public MovieBuilder releaseDate(LocalDate releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }
        public MovieBuilder rating(double rating){
            this.rating = rating;
            return this;
        }public MovieBuilder poster_path(String poster_path){
            this.poster_path = poster_path;
            return this;
        }
        public MovieBuilder overview(String overview){
            this.overview = overview;
            return this;
        }

        public  Movie build() {return new Movie(name, id,
                genre, releaseDate, rating,
                poster_path, overview);}
    }
}
