package use_case.GetWatchList;

import entity.Movie;

import java.util.List;

public class GetWatchListOutputData {
    private final List<String> names;
    private final List<String> poster_urls;

    public GetWatchListOutputData(List<String> names, List<String> poster_urls) {
        this.names = names;
        this.poster_urls = poster_urls;
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getPoster_urls() {
        return poster_urls;
    }
}
