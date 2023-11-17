package data_access;

import entity.Movie;

public interface MovieSavingDataAccessInterface {

    Movie getByName(String name);

    boolean existsByName(String name);
}
