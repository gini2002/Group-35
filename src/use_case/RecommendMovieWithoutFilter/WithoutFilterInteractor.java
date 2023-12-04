package use_case.RecommendMovieWithoutFilter;

import data_access.MovieDataAccessObject;
import entity.Movie;
import data_access.WithoutFilterDAO;

import java.util.*;

/**
 * The interactor class for the "Recommend Movie Without Filter" use case.
 * This class handles the logic of recommending movies based on user's watchlist
 * without applying additional filters. It communicates with data access objects
 * to fetch movie data and presents the results through the output boundary.
 */
public class WithoutFilterInteractor implements WithoutFilterInputBoundary {
//    final WithoutFilterDataAccessInterface withoutFilterDAO;
    final WithoutFilterOutputBoundary WithoutFilterPresenter;
//    private Watchlist watchlist;
    private WithoutFilterDAO withoutFilterDAO;
    private data_access.MovieDataAccessObject movieDAO;

    /**
     * Constructs a new WithoutFilterInteractor with specified dependencies.
     * It initializes the presenter, data access objects for watchlist and movie recommendations.
     *
     * @param WithoutFilterPresenter The presenter to communicate with the user interface.
     * @param withoutFilterDAO The data access object for accessing watchlist data.
     * @param movieDAO The data access object for accessing movie data.
     */
    public WithoutFilterInteractor(WithoutFilterOutputBoundary WithoutFilterPresenter, WithoutFilterDAO withoutFilterDAO, MovieDataAccessObject movieDAO) {
//        this.MovieDataAccessObject = movieDataAccessObject;
        this.WithoutFilterPresenter = WithoutFilterPresenter;
//        this.watchlist = watchlist;
        this.withoutFilterDAO = withoutFilterDAO;
        this.movieDAO = movieDAO;
    }

    /**
     * Executes the process of recommending movies without filters based on the input data.
     * This method retrieves movies from the user's watchlist and recommends movies
     * based on the most frequent genre in the watchlist.
     *
     * @param withoutFilterInputData The input data containing user information for recommendations.
     */

    @Override
    public void execute(WithoutFilterInputData withoutFilterInputData) {
        String username = withoutFilterInputData.getUsername();

        List<Integer> movieIds = withoutFilterDAO.getWatchlistMovies(username);
//        List<Movie> userwatchlist = user.getWatchlist(watchlistID);

        if (movieIds.isEmpty()) {
            WithoutFilterPresenter.WithoutFilterFailView("No movies found in the watchlist");
        } else {
            try {
                List<String> keywords = getKeywordsForWatchlistMovies(movieIds); // 이전에 구현한 메서드로 장르 리스트를 가져옵니다.
                List<String> genres = extractMatchingGenres(keywords);
                String topGenre = findMostFrequentGenre(genres); // 가장 빈번한 장르를 찾습니다.
                List<Movie> withoutFilterMovies = movieDAO.getRecommendedMovies(topGenre); // 해당 장르로 영화를 검색합니다.
                // Prepare a success view with the list of recommended movies
                WithoutFilterOutputData outputData = new WithoutFilterOutputData(withoutFilterMovies);
                WithoutFilterPresenter.WithoutFilterSuccessView(outputData);
            } catch (NoSuchElementException e) {
                System.out.println("without filter: fail case");
                WithoutFilterPresenter.WithoutFilterFailView("movies in watchlist do not have genre");
            }

        }
    }

    /**
     * Retrieves keywords for each movie in the watchlist.
     * This method collects all keywords associated with movies in the user's watchlist.
     *
     * @param movieIds The list of movie IDs in the user's watchlist.
     * @return A list of keywords associated with these movies.
     */

    public List<String> getKeywordsForWatchlistMovies(List<Integer> movieIds) {
        List<String> allKeywords = new ArrayList<>(); // Use a List to include duplicate keywords

        for (int movieId : movieIds) {
            List<String> keywordsForMovie = withoutFilterDAO.getKeywordsForMovie(movieId);
            allKeywords.addAll(keywordsForMovie);
        }

        return allKeywords;
    }

    /**
     * Extracts matching genres from a list of keywords.
     * This method identifies genres that match with the provided keywords.
     *
     * @param keywords The list of keywords to be matched against genres.
     * @return A list of genres that match the keywords.
     */
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

    /**
     * Finds the most frequent genre in a list of genres.
     * This method determines the genre that occurs most frequently in the provided list.
     *
     * @param genres The list of genres to analyze.
     * @return The most frequent genre in the list.
     */

    public String findMostFrequentGenre(List<String> genres) {
        Map<String, Integer> genreCount = new HashMap<>();
        for (String genre : genres) {
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }

        String mostFrequentGenre = Collections.max(genreCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        return mostFrequentGenre;
    }
}

