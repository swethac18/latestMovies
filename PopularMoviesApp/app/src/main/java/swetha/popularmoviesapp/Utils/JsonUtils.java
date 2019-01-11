package swetha.popularmoviesapp.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import swetha.popularmoviesapp.Model.Movie;

/**
 * Created by swetha on 12/29/18.
 */

public class JsonUtils {

    public static Movie parseMoviesJson(String Json){
        final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
        final String IMAGE_SIZE = "w185/";
        List<String> movieTitles = new ArrayList<>();
        List<String> poster_path = new ArrayList<>();
        List<String> releaseDate = new ArrayList<>();
        List<String> voteAverage = new ArrayList<>();
        List<String> plotSynopsis = new ArrayList<>();
        try {
            JSONObject movies = new JSONObject(Json);
            JSONArray results = movies.optJSONArray("results");
            for(int i = 0; i < results.length(); i++) {
                JSONObject result = (JSONObject) results.get(i);
                String title = result.getString("title");
                movieTitles.add(title);
                String path = result.optString("poster_path");
                poster_path.add(IMAGE_BASE_URL+IMAGE_SIZE+path);
                String rDate = result.getString("release_date");
                releaseDate.add(rDate);
                String voteAvg = result.getString("vote_average");
                voteAverage.add(voteAvg);
                String overview = result.getString("overview");
                plotSynopsis.add(overview);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Movie(movieTitles,poster_path,releaseDate,voteAverage,plotSynopsis) ;
    }

    //title, release date, movie poster, vote average, and plot synopsis


}
