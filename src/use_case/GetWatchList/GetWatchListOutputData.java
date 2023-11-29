package use_case.GetWatchList;

import java.util.List;

public class GetWatchListOutputData {
    private final List<String> names;
    private final List<String> poster_urls;
    private final List<Integer> ids;

    private final String logged_in_username;

    public GetWatchListOutputData(List<String> names, List<String> poster_urls, List<Integer> ids, String name) {
        this.names = names;
        this.poster_urls = poster_urls;
        this.ids = ids;
        this.logged_in_username = name;
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getPoster_urls() {
        return poster_urls;
    }

    public List<Integer> getIds(){return ids;}

    public String getLogged_in_username() {
        return logged_in_username;
    }
}
