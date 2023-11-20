package data_access;

import entity.Movie;
import entity.User;
import entity.UserFactory;
import entity.Watchlist;
import entity.MovieFactory;
import entity.SearchHistory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ShareWatchlistDataAccessObject implements ShareWatchlistDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;



    public ShareWatchlistDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);

        headers.put("id", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);
        headers.put("search_history", 4);
        headers.put("watchlist", 5);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("id,username,password,creation_time,search_history,watchlist");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String id = String.valueOf(col[headers.get("id")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String search_history = String.valueOf(col[headers.get("search_history")]);
                    String watchlist = String.valueOf(col[headers.get("watchlist")]);

                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);



                    List<Movie> searchHistoryMovies = trans_to_movie(search_history, "#");
                    SearchHistory searchHistory = new SearchHistory(searchHistoryMovies);
                    //TODO use #?

                            //create SearchHistory object
                            //create movie objects

                    List<Movie> watchlistMovies = trans_to_movie(watchlist, "#");
                    Watchlist watchList = new Watchlist(watchlistMovies);
                    //TODO use #?

                            //create Warchlist object
                            //create movie objects
                    User user = userFactory.create(username, password, ldt, searchHistory, watchList);
                    accounts.put(username, user);
                }
            }
        }

    }

    private List<Movie> trans_to_movie(String col, String splitter) {
        //TODO precondition
        String[] movie_list = col.split(splitter);
        List<Movie> movies = new ArrayList<>();
        for (String num : movie_list) {
            Integer movie_id = Integer.valueOf(num);
            Movie history_movie = get_movie_from_api(movie_id);
            movies.add(history_movie);
        }
        return movies;
    }

    private Movie get_movie_from_api(int movieID) {
        //call api get request
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/collection/18")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTM1NDRjZTMxNTEyYjhlZGMzOWFlYWQyMTdiZWFlZCIsInN1YiI6IjY1MTZlZjJmYzUwYWQyMDBjOTFhNjYwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d5F2KzF4gOHTdMcv3AZzazTgKTGv--FzILbQvLVG9EI")
                .build();

        try {
            Response response = client.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean userExist(String userName) {
        return accounts.containsKey(userName);
    }

    @Override
    public List<Movie> getWatchlist(String userName) {
        User user = accounts.get(userName);
        return user.getWatchlist();
    }

    @Override
    public void setWatchlist(String userName, List<Movie> watchlist) {
        User user = accounts.get(userName);
        Watchlist watchlists = new Watchlist(watchlist);
        user.setSharedWatchlist(userName, watchlists);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime(),
                        user.getSearchHistory(), user.getWatchlist());
                //TODO format id
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
