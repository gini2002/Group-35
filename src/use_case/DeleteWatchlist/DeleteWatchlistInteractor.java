package use_case.DeleteWatchlist;

//import data_access.WatchlistDAO;
//import entity.Movie;

//public class DeleteWatchlistInteractor implements DeleteWatchlistInputBoundary {
//    private final DeleteWatchlistOutputBoundary deleteWatchlistPresenter;
//    private final DeleteWatchlistDataAccessInterface dataAccessInterface;
//
//    public DeleteWatchlistInteractor(DeleteWatchlistOutputBoundary deleteWatchlistPresenter, DeleteWatchlistDataAccessInterface dataAccessInterface) {
//        this.deleteWatchlistPresenter = deleteWatchlistPresenter;
//        this.dataAccessInterface = dataAccessInterface;
//    }
//
//    @Override
//    public void execute(DeleteWatchlistInputData inputData) {
//
//        User user = dataAccessInterface.getUser(inputData.getUserName());
//        Movie movie = inputData.getMovie();
//
//        if (user == null) {
//            deleteWatchlistPresenter.PrepareFailView("User not found");
//            return;
//        }
//
//        List<Movie> watchList = user.getWatchlist();
//
//        boolean movieExists= false;
//        for (Movie m : watchList) {
//            if (m.getName().equals(movie.getName())) {
//                movieExists = true;
//                break;
//            }
//        }
//
//        if (movieExists) {
//            // Use DAO to remove the movie from the watchlist
//            //TODO: after remove movie function update, also update this function >
//            // this part uses the function to delete and pass it to the presenter
//            WatchlistDAO.removeMovieFromWatchlist(inputData.getUserName(), movie);
//
//            DeleteWatchlistOutputData outputData = new DeleteWatchlistOutputData(movie);
//            deleteWatchlistPresenter.PrepareSuccessView(outputData);
//        } else {
//            deleteWatchlistPresenter.PrepareFailView(movie.getName() + " does not exist in the watchlist");
//        }
//    }
//
//}

//public class DeleteWatchlistInteractor implements DeleteWatchlistInputBoundary {
//    private final DeleteWatchlistOutputBoundary deleteWatchlistPresenter;
//    private final DeleteWatchlistDataAccessInterface dataAccessInterface;
//
//    public DeleteWatchlistInteractor(DeleteWatchlistOutputBoundary deleteWatchlistPresenter, DeleteWatchlistDataAccessInterface dataAccessInterface) {
//        this.deleteWatchlistPresenter = deleteWatchlistPresenter;
//        this.dataAccessInterface = dataAccessInterface;
//    }
//
//    @Override
//    public void execute(DeleteWatchlistInputData inputData) {
//
//        User user = dataAccessInterface.getUser(inputData.getUserName());
//        Movie movie = inputData.getMovie();
//
//        if (user == null) {
//            deleteWatchlistPresenter.PrepareFailView("User not found");
//            return;
//        }
//
//        List<Movie> watchList = user.getWatchlist();
//
//        boolean movieExists= false;
//        for (Movie m : watchList) {
//            if (m.getName().equals(movie.getName())) {
//                movieExists = true;
//                break;
//            }
//        }
//
//        if (movieExists) {
//            // Get the instance of WatchlistDAO from the dataAccessInterface
//            WatchlistDAO watchlistDAO = dataAccessInterface.getWatchlistDAO();
//
//            // Use the instance to remove the movie from the watchlist
//            watchlistDAO.removeMovieFromWatchlist(inputData.getUserName(), movie.getID());
//
//            DeleteWatchlistOutputData outputData = new DeleteWatchlistOutputData(movie);
//            deleteWatchlistPresenter.PrepareSuccessView(outputData);
//        } else {
//            deleteWatchlistPresenter.PrepareFailView(movie.getName() + " does not exist in the watchlist");
//        }
//    }
//}

import data_access.WatchlistDAO;
import entity.Movie;

import java.util.List;

public class DeleteWatchlistInteractor implements DeleteWatchlistInputBoundary {
    private final DeleteWatchlistOutputBoundary deleteWatchlistPresenter;
    private final DeleteWatchlistDataAccessInterface deleteWatchListDAI;

    public DeleteWatchlistInteractor(DeleteWatchlistOutputBoundary deleteWatchlistPresenter, DeleteWatchlistDataAccessInterface dataAccessInterface) {
        this.deleteWatchlistPresenter = deleteWatchlistPresenter;
        this.deleteWatchListDAI = dataAccessInterface;
    }

    @Override
    public void execute(DeleteWatchlistInputData inputData) {
        String username = inputData.getUserName();
        Movie movie = inputData.getMovie();
        List<Integer> all_movie = deleteWatchListDAI.getWatchlistMoviesID(username);

        if (all_movie.contains(movie.getID())) {
            deleteWatchListDAI.removeMovieFromWatchlist(username, movie.getID());
            DeleteWatchlistOutputData outputData = new DeleteWatchlistOutputData(movie);
            deleteWatchlistPresenter.PrepareSuccessView(outputData);
        } else {

            deleteWatchlistPresenter.PrepareFailView(movie.getName() + " doesn't exists");

        }
    }
}



