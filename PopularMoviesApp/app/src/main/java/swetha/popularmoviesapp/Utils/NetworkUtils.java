package swetha.popularmoviesapp.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by swetha on 12/29/18.
 */

public class NetworkUtils {
    final static String MOVIE_BASE_URL="https://api.themoviedb.org/3/movie/";
    final static String PARAM_QUERY = "api_key";
    final static String PARAM_SORT = "sort_by";
    //final static String sortBY="vote_count.desc";
    final static String api_key = readAPIKey();

    private static String readAPIKey() {
        return "8031206cc3f22d12d93e71f60a8f4d96";
    }
    public static URL buildUrl(String sortBY) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL+sortBY).buildUpon()
                .appendQueryParameter(PARAM_QUERY, api_key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("url",url.toString());
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
