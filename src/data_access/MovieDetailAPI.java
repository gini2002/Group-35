package data_access;

import java.util.ArrayList;
import java.util.List;

public interface MovieDetailAPI {
    boolean existbyname();

    List<List> getdetailMovie(int id);
}
