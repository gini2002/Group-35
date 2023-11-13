package data_access;

import entity.Movie;

public interface MovieSavingDataAccessInterface {
    void save(Movie movie);

    Movie getById(int id);

    Movie getByName(String name);

    boolean existsByName(String name);
}
