package data_access;

import entity.Movie;
import entity.MovieFactory;
import use_case.GetDetailMovie.GetDetailMovieDataAccessInterface;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class MovieSavingObject implements MovieSavingDataAccessInterface, GetDetailMovieDataAccessInterface {
    private final File csvFile;
    private Map<Integer, Movie> id_movie;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Movie> movienameMap = new HashMap<>();

    private MovieFactory movieFactory;

    public MovieSavingObject(String csvPath, MovieFactory movieFactory) throws IOException {
        this.movieFactory = movieFactory;

        csvFile = new File(csvPath);
        headers.put("MovieId", 0);
        headers.put("MovieTitle", 1);
        headers.put("MovieGenre", 2);
        headers.put("MovieActors", 3);
        headers.put("MovieReleasedDate", 4);
        headers.put("MovieRating", 5);
        headers.put("MovieDirector", 6);
        headers.put("MovieOverView", 7);
        headers.put("MoviePoster", 8);

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
                    int id = Integer.parseInt(col[headers.get("MovieId")]);
                    String title = String.valueOf(col[headers.get("MovieTitle")]);
                    List<String> genre = Collections.singletonList(String.valueOf(col[headers.get("MovieGenre")]));
                    //TODO:How to get a collection of movies from csv file
                    String releasedDate = String.valueOf(col[headers.get("MovieReleasedDate")]);
                    double rating = Integer.parseInt(col[headers.get("MovieRating")]);
                    String overview = String.valueOf(col[headers.get("MovieOverView")]);
                    LocalDate ldt = LocalDate.parse(releasedDate);
                    String poster_path = String.valueOf(col[headers.get("MoviePoster")]);
                    Movie movie = movieFactory.create(id, title, genre, ldt, rating,
                            overview, poster_path);
                    movienameMap.put(title, movie);
                }
            }
        }
    }


    @Override
    public Movie getByName(String name) {
        return movienameMap.get(name);
    }


    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Movie movie : movienameMap.values()) {
                String line = String.format("%s,%s,%s,%s,%s",
                        movie.getGenre(),
                        movie.getReleaseDate(), movie.getRating(),
                        movie.getOverview(), movie.getPoster_path());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a movie exists with movie name identifier.
     * @param identifier the movie name to check.
     * @return whether a movie exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return movienameMap.containsKey(identifier);
    }

}

