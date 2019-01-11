package swetha.popularmoviesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import swetha.popularmoviesapp.Model.Movie;
import swetha.popularmoviesapp.Utils.JsonUtils;
import swetha.popularmoviesapp.Utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieOnClickHandler {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TextView mErrorMessageTextView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spinner_sort, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        final Spinner spinner = (Spinner) item.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sortby,R.layout.simple_spinner_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = spinner.getSelectedItem().toString();
                if(item.equals("Most Popular")) {
                    Log.i("Show", "Most Popular");
                    new MoviesAsyncTask().execute("popular");
                }
                else if(item.equals("Top Rated")) {
                    Log.i("Show", "Top Rated");
                    new MoviesAsyncTask().execute("top_rated");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                recyclerView.setVisibility(View.INVISIBLE);
                mErrorMessageTextView.setVisibility(View.VISIBLE);
                mErrorMessageTextView.setText("Select a sort criteria to display movies");
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mErrorMessageTextView = findViewById(R.id.errorTextView);

    }

    @Override
    public void onClick(String posterPath,String title,String releaseDate,String voteAvg,String overview) {
        Class destinationClass = DetailActivity.class;
        Intent intent = new Intent(MainActivity.this,destinationClass);
        intent.putExtra(Intent.EXTRA_TEXT,new String[]{posterPath,title,releaseDate,voteAvg,overview});
        startActivity(intent);
    }

    public class MoviesAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG,"inside:doInBackground");
            URL apiURL = NetworkUtils.buildUrl(strings[0]);
            String apiResponse = "";
            try {
                apiResponse = NetworkUtils.getResponseFromHttpUrl(apiURL);
                if(apiResponse.isEmpty()) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    mErrorMessageTextView.setVisibility(View.VISIBLE);
                    mErrorMessageTextView.setText("Data not available to display");
                }
                //Log.i("response",apiResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return apiResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            Movie movie = JsonUtils.parseMoviesJson(s);
            initGridLayout(movie);
        }
    }

    public void initGridLayout(Movie movie){
        Log.d(TAG,"inside:initGridLayout");
        ButterKnife.bind(this);
        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this,this);
        movieAdapter.setMoviesData(movie);
        recyclerView.setAdapter(movieAdapter);
       // recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
    }



}
