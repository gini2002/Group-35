package use_case.ShareWatchlist;


import entity.Movie;

import java.util.List;

public class ShareWatchlistInteractor implements ShareWatchlistInputBoundary{
    private final ShareWatchlistOutputBoundary shareWatchlistOutputBoundary;
    private final ShareWatchlistDataAccessInterface dataAccessInterface;

    /**
     * initiate a share watchlist interactor.
     * @param shareWatchlistOutputBoundaryy out put boudnary to be used.
     * @param dataAccessInterface interface that can access to csv file.
     */
    public ShareWatchlistInteractor(ShareWatchlistOutputBoundary shareWatchlistOutputBoundaryy, ShareWatchlistDataAccessInterface dataAccessInterface) {
        this.shareWatchlistOutputBoundary = shareWatchlistOutputBoundaryy;
        this.dataAccessInterface = dataAccessInterface;
    }

    /**
     * save sender's watchlist to receiver's shared watchlist.
     * @param inputData store the sender and receiver's userName.
     */
    @Override
    public void execute(ShareWatchlistInputData inputData) {
        //1. check if receiver exists
        //2. check if sender's watchlist is empty.
        //3. get sender's watchlist and add to receiver.

        String SenderName = inputData.getSenderName();
        String ReceiverName = inputData.getReceiverName();

        if (! dataAccessInterface.userExist(ReceiverName)) {
            shareWatchlistOutputBoundary.prepareFailView("the user does not exist");
        } else {
            List<Movie> watchlist = dataAccessInterface.getWatchlistByUsername(SenderName);
            if (watchlist.isEmpty()) {
                shareWatchlistOutputBoundary.prepareFailView("the watchlist is empty");
            } else {
                dataAccessInterface.setWatchlist(ReceiverName, watchlist);
                ShareWatchlistOutputData outputData = new ShareWatchlistOutputData(ReceiverName);
                shareWatchlistOutputBoundary.prepareSuccessView(outputData);
            }
        }
    }
}
