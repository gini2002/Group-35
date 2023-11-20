package entity;

import java.time.LocalDate;
import java.util.List;

public interface MovieFactory {
    Movie create(int id, String name, List<String> genre, LocalDate releaseDate,
                 double rating, String overview, String poster_path);
}