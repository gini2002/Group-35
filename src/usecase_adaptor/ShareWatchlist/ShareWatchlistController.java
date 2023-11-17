package usecase_adaptor.ShareWatchlist;

import use_case.ShareWatchlist.ShareWatchlistInputBoundary;
import use_case.ShareWatchlist.ShareWatchlistInputData;

public class ShareWatchlistController {

    private final ShareWatchlistInputBoundary shareWatchlistInputBoundary;

    public ShareWatchlistController(ShareWatchlistInputBoundary shareWatchlistInputBoundary) {
        this.shareWatchlistInputBoundary = shareWatchlistInputBoundary;
    }
    public void execute(String userName1, String userName2) {
        ShareWatchlistInputData inputData = new ShareWatchlistInputData(userName1, userName2);
        shareWatchlistInputBoundary.execute(inputData);
    }
}
