package entity;

import java.util.ArrayList;
import java.util.List;

    public class Watchlist {
        private List<Movie> list = new ArrayList<>();

        public Watchlist() {
        }

        public Watchlist(List<Movie> movies) {
            this.list = movies;
        }

        public void addMovie(Movie movie) {
            list.add(movie);
        }

        public List<Movie> getWatchlist() {
            return list;
        }
    }
