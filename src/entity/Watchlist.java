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

        @Override
        public String toString() {
            String result = "";
            for (Movie movie:list) {
                int id = movie.getID();
                result = result + id + "#";
            }
            if (result.isEmpty()) {
                return result;
            } else {
                return result.substring(0, result.length()-2);
            }
        }
    }
