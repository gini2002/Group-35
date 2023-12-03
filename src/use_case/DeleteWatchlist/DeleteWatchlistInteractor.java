package use_case.DeleteWatchlist;

import data_access.AddToWatchlistDataAccessObject;
import data_access.WatchlistDAO;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import use_case.AddToWatchlist.AddToWatchlistDataAccessInterface;

import java.util.List;

/**
 * Interactor for the delete watchlist use case.
 * This class handles the business logic of deleting a movie from a user's watchlist.
 * It communicates between the data access layer and the presentation layer.
 */

public class DeleteWatchlistInteractor implements DeleteWatchlistInputBoundary {
    private final DeleteWatchlistOutputBoundary deleteWatchlistPresenter;
    private final DeleteWatchlistDataAccessInterface dataAccessInterface;

    /**
     * Constructs a DeleteWatchlistInteractor.
     *
     * @param deleteWatchlistPresenter The presenter interface for outputting results of the deletion process.
     * @param dataAccessInterface The data access interface for interacting with the data layer.
     */

    public DeleteWatchlistInteractor(DeleteWatchlistOutputBoundary deleteWatchlistPresenter, DeleteWatchlistDataAccessInterface dataAccessInterface) {
        this.deleteWatchlistPresenter = deleteWatchlistPresenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    /**
     * Executes the operation of deleting a movie from a user's watchlist.
     * Retrieves the user's watchlist, checks if the movie exists in the watchlist, and performs the deletion.
     * Notifies the presenter of the success or failure of the deletion operation.
     *
     * @param inputData The input data containing the details of the movie to be deleted and the user's username.
     */

    @Override
    public void execute(DeleteWatchlistInputData inputData) {
        DeleteWatchlistDataAccessInterface DAO = new WatchlistDAO(
                dataAccessInterface.getPath(), new CommonUserFactory());
        User user = DAO.getUser(inputData.getUserName());
        String username = inputData.getUserName();
        Movie movie = inputData.getMovie();
        List<Movie> watchList = user.getWatchlist();
        boolean movieFound = false;

        for (Movie movies : watchList) {
            if (movies.getID() == movie.getID()) {
                movieFound = true;
                DAO.saveMovie(inputData.getUserName(), movie);
                DeleteWatchlistOutputData outputData = new DeleteWatchlistOutputData(movie);
                deleteWatchlistPresenter.PrepareSuccessView(outputData);
                break;  // This will exit the loop once the matching movie is found
            }
        }
        if (!movieFound) {
            deleteWatchlistPresenter.PrepareFailView(movie.getName() + " doesn't exist");
        }



    }
}




