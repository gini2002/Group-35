package use_case.RecommendMovieWithoutFilter;

import data_access.MovieDataAccessObject;
import entity.Movie;
import entity.Watchlist;

import java.util.*;


public class WithoutFilterController {
    private Watchlist watchlist;
    private WithoutFilterDAO withoutFilterDAO;
    private MovieDataAccessObject movieDAO;

    public WithoutFilterController(Watchlist watchlist, WithoutFilterDAO withoutFilterDAO, MovieDataAccessObject movieDAO) {
        this.watchlist = watchlist;
        this.withoutFilterDAO = withoutFilterDAO;
        this.movieDAO = movieDAO;
    }

    public List<Integer> getMovieIdsFromWatchlist() {
        List<Movie> wlist = watchlist.getWatchlist(); // 이 function 어디에서 콜 해야할지 물어보기
        List<Integer> movieIds = new ArrayList<>();
        for (Movie movie : wlist) {
            movieIds.add(movie.getID()); // 이 function 도
        }
        return movieIds;
    }

    public List<String> getKeywordsForWatchlistMovies(List<Integer> movieIds) {
        List<String> allKeywords = new ArrayList<>(); // Use a List to include duplicate keywords

        for (int movieId : movieIds) {
            List<String> keywordsForMovie = withoutFilterDAO.getKeywordsForMovie(movieId);
            allKeywords.addAll(keywordsForMovie);
        }

        return allKeywords;
    }

    public List<String> extractMatchingGenres(List<String> keywords) {
        List<String> genres = Arrays.asList("Horror", "Romance", "Action", "Comedy", "Drama", "Science Fiction", "Fantasy", "Thriller", "Mystery", "Documentary", "Animation", "Adventure");
        List<String> matchedGenres = new ArrayList<>();

        for (String keyword : keywords) {
            for (String genre : genres) {
                if (keyword.toLowerCase().contains(genre.toLowerCase())) {
                    matchedGenres.add(genre);
                }
            }
        }

        return matchedGenres;
    }

    public String findMostFrequentGenre(List<String> genres) {
        Map<String, Integer> genreCount = new HashMap<>();
        for (String genre : genres) {
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }

        String mostFrequentGenre = Collections.max(genreCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        return mostFrequentGenre;
    }

    public List<Movie> getMovieWithoutFilter() {
        List<Integer> movieIds = getMovieIdsFromWatchlist();
        List<String> keywords = getKeywordsForWatchlistMovies(movieIds); // 이전에 구현한 메서드로 장르 리스트를 가져옵니다.
        List<String> genres = extractMatchingGenres(keywords);
        String topGenre = findMostFrequentGenre(genres); // 가장 빈번한 장르를 찾습니다.
        return movieDAO.fetchMovies(topGenre); // 해당 장르로 영화를 검색합니다.
    }
}
