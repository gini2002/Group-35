package data_access;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MovieDetailAPI {
    Movie getdetailMovie(int id);

    boolean existsById(int id);
}
