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
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);
        headers.put("search_history", 3);
        headers.put("watchlist", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
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
            int movie_id = Integer.parseInt(num);
            Movie history_movie = get_movie_from_api(movie_id);
            movies.add(history_movie);
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
                String line = String.format("%s,%s,%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime());
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
        return names;}


    public void clearAllUsers(){
        if (csvFile.exists()) {
            try {
                // Delete the existing file
                if (csvFile.delete()) {
                    // Recreate an empty file
                    if (csvFile.createNewFile()) {
                        System.out.println("Data in the CSV file has been cleared.");
                    } else {
                        System.err.println("Failed to create an empty file.");
                    }
                } else {
                    System.err.println("Failed to delete the existing file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The CSV file does not exist.");
        }
    }

}
