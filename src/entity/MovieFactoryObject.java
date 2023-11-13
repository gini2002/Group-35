package entity;

import java.time.LocalDate;
import java.util.List;

public class MovieFactoryObject implements MovieFactory{
    @Override
    public Movie create(int id, String name, List<String> genre,
                        List<String> actors, LocalDate releaseDate,
                        double rating, String overview, String director, String poster_path){
        Movie movie = new Movie(name);
        movie.setActors(actors);
        movie.setDirector(director);
        movie.setId(id);
        movie.setRating(rating);
        movie.setReleaseDate(releaseDate);
        movie.setOverview(overview);
        movie.setGenre(genre);
        movie.setPoster_path(poster_path);
        return movie;}
}
