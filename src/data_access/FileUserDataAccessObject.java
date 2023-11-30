package data_access;

import entity.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
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

                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    List<Movie> searchHistoryMovies = new ArrayList<>();
                    SearchHistory searchHistory = new SearchHistory(searchHistoryMovies);

                    //create SearchHistory object
                    //create movie objects

                    List<Movie> watchlistMovies = trans_to_movie(watchlist, "#");
                    Watchlist watchList = new Watchlist(watchlistMovies);
                    User user = userFactory.create(username, password, ldt, searchHistory, watchList);
                    user.setId(getId());
                    accounts.put(username, user);
                }
            }
        }
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
        return movies;}
    private Movie get_movie_from_api(int movieID) {

        //call api get request
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/collection/" + String.valueOf(movieID))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTM1NDRjZTMxNTEyYjhlZGMzOWFlYWQyMTdiZWFlZCIsInN1YiI6IjY1MTZlZjJmYzUwYWQyMDBjOTFhNjYwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d5F2KzF4gOHTdMcv3AZzazTgKTGv--FzILbQvLVG9EI")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                String name = String.valueOf(responseBody.getJSONObject("name"));
                return new Movie(name, movieID);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s",
                        user.getId(), user.getName(), user.getPassword(), user.getCreationTime(),
                        new SearchHistory(), new Watchlist(), new HashMap<String, Watchlist>());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> names = new ArrayList<>(accounts.keySet());
        return names;
    }

    @Override
    public void clearAllUsers() {

    }

    private int getId() {
        return accounts.size();
    }

}
