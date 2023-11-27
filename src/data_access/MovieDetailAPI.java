package data_access;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MovieDetailAPI {
    boolean existbyname();

    Movie getdetailMovie(int id);
}
