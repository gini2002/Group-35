package data_access;

import entity.Movie;
import entity.MovieFactory;
import entity.User;
import use_case.GetWatchList.GetWatchListDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetWatchListDAO implements GetWatchListDataAccessInterface
        {
    private final File csvFile;
    private final MovieDetailAccessAPI movieDetailAccessAPI = new MovieDetailAccessAPI();

    private final Map<String, Integer> headers = new HashMap<>();

    private final Map<String, List<Integer>> usernameToWatchlist = new HashMap<>();

    public GetWatchListDAO(String csvPath){
        csvFile = new File(csvPath);

        if (csvFile.length() == 0) {
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("id,username,password,creation_time,search_history,watchlist,shared_watchlist");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[1]);
                    String[] watchlist = String.valueOf(col[5]).split("#");
                    List<Integer> watchlist2 = new ArrayList<>();
                    for (String i : watchlist) {
                        watchlist2.add(Integer.valueOf(i));
                    }
                    usernameToWatchlist.put(username, watchlist2);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Movie> getWatchlistMovies(String name) {
        List<Integer> watchlist_only_id = usernameToWatchlist.get(name);
        List<Movie> result = new ArrayList<>();
        for (int i: watchlist_only_id) {
            result.add(movieDetailAccessAPI.getdetailMovie(i));
        }
        return result;
    }

    // @Override
    //public void add_to_watchlist(User user, int movie_id) {
            //        Movie movie_added = movieDetailAccessAPI.getdetailMovie(movie_id);
            //        List<Movie> watchlist = user.getWatchlist();
            //        watchlist.add(movie_added);
            //        ArrayList<Integer> watchlist2 = new ArrayList<>();
            //        for (Movie movie: watchlist){
            //            watchlist2.add(movie.getID());
            //        }
            //        usernameToWatchlist.replace(user.getName(),watchlist2);
            //        // TODO: not sure if replace can update the value in the hashmap
            //                //.put(user.getName(), watchlist2);
            //        this.save();
    //}

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (String username : usernameToWatchlist.keySet()) {
                String line = String.format("%s,%s", username,
                        String.valueOf(usernameToWatchlist.get(username)));
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
