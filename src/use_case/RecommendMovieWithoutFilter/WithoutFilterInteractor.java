package use_case.RecommendMovieWithoutFilter;

import data_access.MovieDataAccessObject;
import entity.Movie;
import entity.Watchlist;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterDAO;

import java.util.*;

public class WithoutFilterInteractor implements WithoutFilterInputBoundary {
//    final WithoutFilterDataAccessInterface withoutFilterDAO;
    final WithoutFilterOutputBoundary WithoutFilterPresenter;
//    private Watchlist watchlist;
    private WithoutFilterDAO withoutFilterDAO;
    private data_access.MovieDataAccessObject movieDAO;

    public WithoutFilterInteractor(WithoutFilterOutputBoundary WithoutFilterPresenter, WithoutFilterDAO withoutFilterDAO, MovieDataAccessObject movieDAO) {
//        this.MovieDataAccessObject = movieDataAccessObject;
        this.WithoutFilterPresenter = WithoutFilterPresenter;
//        this.watchlist = watchlist;
        this.withoutFilterDAO = withoutFilterDAO;
        this.movieDAO = movieDAO;
    }

    @Override
    public void execute(WithoutFilterInputData withoutFilterInputData) {
        String username = withoutFilterInputData.getUsername();
        // Check if the watchlistid is not null or empty
//        if (watchlistID == null || watchlistID.isEmpty()) {
//            WithoutFilterPresenter.WithoutFilterFailView("Invalid ID. Check watchlistID");
//            return;
//        }

        // Retrieve recommended movies based on the keyword from the data access
//        List<Movie> recommendedMovies = searchByNameDataAccessObject.getRecommendedMovies(keyword);
        // Retrieve watchlist based on the userid and watchlistid
        //TODO: ask how the get watchlist is implemented and use it to get the watchlist of the user
        // (is it in a usecase or the entity?)
        List<Integer> movieIds = withoutFilterDAO.getWatchlistMovies(username);
//        List<Movie> userwatchlist = user.getWatchlist(watchlistID);

        if (movieIds.isEmpty()) {
            WithoutFilterPresenter.WithoutFilterFailView("No movies found in the watchlist");
        } else {
//            List<Integer> movieIds = withoutFilterDAO.getWatchlistMovies(username);
            List<String> keywords = getKeywordsForWatchlistMovies(movieIds); // 이전에 구현한 메서드로 장르 리스트를 가져옵니다.
            List<String> genres = extractMatchingGenres(keywords);
            String topGenre = findMostFrequentGenre(genres); // 가장 빈번한 장르를 찾습니다.
            List<Movie> withoutFilterMovies = movieDAO.getRecommendedMovies(topGenre); // 해당 장르로 영화를 검색합니다.
            // Prepare a success view with the list of recommended movies
            WithoutFilterOutputData outputData = new WithoutFilterOutputData(withoutFilterMovies);
            WithoutFilterPresenter.WithoutFilterSuccessView(outputData);

        }
    }

//    public List<Integer> getMovieIdsFromWatchlist() {
//        List<Movie> wlist = watchlist.getWatchlist(); // 이 function 어디에서 콜 해야할지 물어보기
//        List<Integer> movieIds = new ArrayList<>();
//        for (Movie movie : wlist) {
//            movieIds.add(movie.getID()); // 이 function 도
//        }
//        return movieIds;
//    }

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
}

