package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailAccessAPI implements MovieDetailAPI{
    private static final String API_URL = "https://grade-logging-api.chenpan.ca/api/grade";
    // load API_TOKEN from env variable.
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }



    @Override
    public List<List> getdetailMovie(int id) {
        String movie_id = String.valueOf(id);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://api.themoviedb.org/3/movie/movie_id=%s?language=en-US", movie_id))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                List<List> result = new ArrayList<>();
                JSONObject title = responseBody.getJSONObject("title");
                JSONObject poster_path = responseBody.getJSONObject("poster_path");
                JSONObject overview = responseBody.getJSONObject("overview");
                JSONObject genre = responseBody.getJSONObject("genres");
                JSONObject id2 = responseBody.getJSONObject("id");
                return result;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existbyname() {
        return false;
    }
}
