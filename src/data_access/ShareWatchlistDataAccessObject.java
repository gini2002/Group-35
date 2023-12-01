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

import org.json.JSONObject;
import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ShareWatchlistDataAccessObject implements ShareWatchlistDataAccessInterface {

    private final File csvFile;

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;


    /**
     * initiate the DAO.
     * @param csvPath the path of file that saves information.
     * @param userFactory factory to produce user.
     */
    public ShareWatchlistDataAccessObject(String csvPath, UserFactory userFactory) {
        headers = new LinkedHashMap<>();
        try {
            this.userFactory = userFactory;


            csvFile = new File(csvPath);

            headers.put("id", 0);
            headers.put("username", 1);
            headers.put("password", 2);
            headers.put("creation_time", 3);
            headers.put("search_history", 4);
            headers.put("watchlist", 5);
            headers.put("shared_watchlist", 6);

            if (csvFile.length() == 0) {
                save();
            } else {

                try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                    String header = reader.readLine();

                    // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                    assert header.equals("id,username,password,creation_time,search_history,watchlist,shared_watchlist");

                    String row;
                    while ((row = reader.readLine()) != null) {
                        String[] col = row.split(",");
                        String id = String.valueOf(col[headers.get("id")]);
                        String username = String.valueOf(col[headers.get("username")]);
                        String password = String.valueOf(col[headers.get("password")]);
                        String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                        String search_history = String.valueOf(col[headers.get("search_history")]);
                        String watchlist = String.valueOf(col[headers.get("watchlist")]);
                        String shared_watchlist = String.valueOf(col[headers.get("shared_watchlist")]);
                        // format of shared_watchlist: name1:1%2%3#name2:4%5%6

                        LocalDateTime ldt = LocalDateTime.parse(creationTimeText);


                        List<Movie> searchHistoryMovies = trans_to_movie(search_history, "#");
                        SearchHistory searchHistory = new SearchHistory(searchHistoryMovies);

                        //create SearchHistory object
                        //create movie objects

                        List<Movie> watchlistMovies = trans_to_movie(watchlist, "#");
                        Watchlist watchList = new Watchlist(watchlistMovies);

                        Map<String, Watchlist> sharedWatchlist = trans_to_shared_watchlist(
                                shared_watchlist, "#", ":", "%");

                        //create Warchlist object
                        //create movie objects
                        User user = userFactory.create(username, password, ldt, searchHistory, watchList);
                        user.setId(Integer.parseInt(id));
                        user.setCompleteSharedWatchlist(sharedWatchlist);
                        accounts.put(username, user);
                    }   }
            }
        } catch(IOException e) {
            throw new RuntimeException();
        }

    }

    private Map<String, Watchlist> trans_to_shared_watchlist(String col, String UserSplitter,
                                                             String EachUserSplitter, String MovieSplitter) {
        String[] info = col.split(UserSplitter);
        Map<String, Watchlist> user_watchlist_map = new HashMap<>();
        for (String each_info: info) {
            Map<String, List<Movie>> map = trans_to_each_user(each_info, EachUserSplitter, MovieSplitter);
            String userName = map.keySet().toString();
            Watchlist watchlist = new Watchlist(map.get(userName));
            user_watchlist_map.put(userName, watchlist);
        }
        return user_watchlist_map;
    }

    private Map<String, List<Movie>> trans_to_each_user(String col, String EachUserSplitter, String MovieSplitter) {
        String[] user_list = col.split(EachUserSplitter);
        Map<String, List<Movie>> map = new HashMap<>();
        if (user_list.length == 2) {
            map.put(user_list[0], trans_to_movie(user_list[1], MovieSplitter));}
        return map;
    }

    private List<Movie> trans_to_movie(String col, String splitter) {
        String[] movie_list = col.split(splitter);
        List<Movie> movies = new ArrayList<>();
        for (String num : movie_list) {
            try {
                int movie_id = Integer.parseInt(num);
                Movie history_movie = get_movie_from_api(movie_id);
                movies.add(history_movie);
            } catch (NumberFormatException e) {
                System.out.println("file error");
            }
        }
        return movies;
    }

    private Movie get_movie_from_api(Integer movieID) {

        //call api get request
        OkHttpClient client = new OkHttpClient();;

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/"+String.valueOf(movieID)+"?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTM1NDRjZTMxNTEyYjhlZGMzOWFlYWQyMTdiZWFlZCIsInN1YiI6IjY1MTZlZjJmYzUwYWQyMDBjOTFhNjYwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d5F2KzF4gOHTdMcv3AZzazTgKTGv--FzILbQvLVG9EI")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            if (response.code() == 200) {
                System.out.println("success");
                JSONObject responseBody = new JSONObject(response.body().string());
                String name = String.valueOf(responseBody.getString("original_title"));
                return new Movie(name, movieID);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     *
     * @param userName of the user.
     * @return whether the user exists.
     */
    @Override
    public boolean userExist(String userName) {
        return accounts.containsKey(userName);
    }

    /**
     *
     * @param userName the name of user.
     * @return the watchlist of the user.
     */
    @Override
    public List<Movie> getWatchlistByUsername(String userName) {
        User user = this.accounts.get(userName);
        return user.getWatchlist();
    }

    /**
     * set watchlist to receiver user's shared watchlist.
     * @param userName the name of user.
     * @param watchlist a watchlist.
     */
    @Override
    public void setWatchlist(String userName, List<Movie> watchlist) {
        User user = accounts.get(userName);
        Watchlist watchlists = new Watchlist(watchlist);
        user.setSharedWatchlist(userName, watchlists);
        save();
    }

    /**
     *
     * @param userName of user.
     * @return the user object of user.
     */
    @Override
    public User getUser(String userName) {
        return accounts.get(userName);
    }


    /**
     * save changes in file.
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s",
                        user.getId(), user.getName(), user.getPassword(), user.getCreationTime(),
                        list_to_movie_string(user.getSearchHistory()),
                        list_to_movie_string(user.getWatchlist()),
                        user.getSharedWatchlist());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String list_to_movie_string(List<Movie> movies) {
        if (movies.isEmpty()) {
            return "";
        }
        String result = "";
        for (Movie movie:movies) {
            int id = movie.getID();
            result = result + id + "#";
        }
        return result.substring(0, result.length() - 1);

    }
}
