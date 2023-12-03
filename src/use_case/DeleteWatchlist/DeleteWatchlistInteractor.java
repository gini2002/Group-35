package use_case.DeleteWatchlist;

import data_access.AddToWatchlistDataAccessObject;
import data_access.WatchlistDAO;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import use_case.AddToWatchlist.AddToWatchlistDataAccessInterface;

import java.util.List;

public class DeleteWatchlistInteractor implements DeleteWatchlistInputBoundary {
    private final DeleteWatchlistOutputBoundary deleteWatchlistPresenter;
    private final DeleteWatchlistDataAccessInterface dataAccessInterface;

    public DeleteWatchlistInteractor(DeleteWatchlistOutputBoundary deleteWatchlistPresenter, DeleteWatchlistDataAccessInterface dataAccessInterface) {
        this.deleteWatchlistPresenter = deleteWatchlistPresenter;
        this.dataAccessInterface = dataAccessInterface;
    }

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




