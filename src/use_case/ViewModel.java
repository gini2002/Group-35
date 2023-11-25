package use_case;

import data_access.MovieDataAccessObject;
import entity.Movie;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class WatchlistViewModel {
    private MovieDataAccessObject movieDAO;

    public WatchlistViewModel(MovieDataAccessObject movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getRecommendedMovies(int userId) {
        List<Movie> watchlist = movieDAO.getWatchlist(userId);
        String mostCommonGenre = findMostCommonGenre(watchlist);
        return movieDAO.getMoviesByGenre(mostCommonGenre);
    }

    private String findMostCommonGenre(List<Movie> watchlist) {
        Map<String, Long> genreCount = watchlist.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));

        return Collections.max(genreCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

//    findMostCommonGenre(watchlist) {
//        const genreCount = {};
//        watchlist.forEach(movie => {
//        if (genreCount[movie.genre]) {
//            genreCount[movie.genre]++;
//        } else {
//            genreCount[movie.genre] = 1;
//        }
//        });
//        return Object.keys(genreCount).reduce((a, b) => genreCount[a] > genreCount[b] ? a : b);
//    }
}

