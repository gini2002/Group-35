package data_access;

import entity.User;
import use_case.DeleteWatchlist.DeleteWatchlistDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WatchlistDAO implements DeleteWatchlistDataAccessInterface {
    private final File csvFile;
    private final Map<String, List<Integer>> usernameToWatchlist = new HashMap<>();

    public WatchlistDAO(String csvPath) throws FileNotFoundException {
        csvFile = new File(csvPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[1];
                List<Integer> watchlist = parseWatchlist(parts[5]);
                usernameToWatchlist.put(username, watchlist);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        }
    }


    public WatchlistDAO getWatchlistDAO() {
        // In this case, just return 'this', since WatchlistDAO itself is being used.
        return this;
    }

    public boolean removeMovieFromWatchlist(String username, int movieId) {
        List<Integer> watchlist = usernameToWatchlist.get(username);
        if (watchlist != null && watchlist.remove(Integer.valueOf(movieId))) {
            save();
            return true;
        }
        return false;
    }

    private List<Integer> parseWatchlist(String watchlistStr) {
        // Assuming the watchlist is stored as a comma-separated string of movie IDs
        List<Integer> watchlist = new ArrayList<>();
        for (String idStr : watchlistStr.split("#")) {
            watchlist.add(Integer.parseInt(idStr.trim()));
        }
        return watchlist;
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Map.Entry<String, List<Integer>> entry : usernameToWatchlist.entrySet()) {
                String line = entry.getKey() + "," + String.join("#", entry.getValue().toString());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file: " + e.getMessage(), e);
        }
    }
}

