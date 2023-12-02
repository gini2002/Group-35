package use_case.AddToWatchlist;

import entity.User;
import entity.Movie;

import java.util.List;


public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary{
    private final AddToWatchlistOutputBoundary addToWatchlistPresenter;
    private final AddToWatchlistDataAccessInterface dataAccessInterface;

    /**
     * Initiate an add to watchlist interactor.
     * @param addToWatchlistPresenter presenter
     * @param dataAccessInterface an object implement this interface
     */
    public AddToWatchlistInteractor(AddToWatchlistOutputBoundary addToWatchlistPresenter, AddToWatchlistDataAccessInterface dataAccessInterface) {
        this.addToWatchlistPresenter = addToWatchlistPresenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    /**
     * add movie in input data to the user's watchlist.
     * @param inputData the input data object store the input data.
     */
    @Override
    public void execute(AddToWatchlistInputData inputData) {
        // 1. find the user
        // 2. check if already exist
        //      1. if exist
        //      2. presenter prepare failview
        //      1. if not exist
        //      2. add watchlist to user + save data
        //      3. presenter prepare successview

        User user = dataAccessInterface.getUser(inputData.getUserName());
        Movie movie = inputData.getMovie();
        List<Movie> watchList = user.getWatchlist();
        int count = 0;
        for (Movie movies : watchList) {
            if (movies.getID() == movie.getID()) {
                count = count + 1;
            }
        }
        if (count == 0) {
            dataAccessInterface.saveMovie(inputData.getUserName(), movie);
            AddToWatchlistOutputData outputData = new AddToWatchlistOutputData(movie);
            addToWatchlistPresenter.PrepareSuccessView(outputData);
        } else {
            addToWatchlistPresenter.PrepareFailView(movie.getName() + " already exists");
        }





    }

}
