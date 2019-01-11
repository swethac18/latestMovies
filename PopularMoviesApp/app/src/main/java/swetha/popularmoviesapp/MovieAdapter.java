package swetha.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import swetha.popularmoviesapp.Model.Movie;

/**
 * Created by swetha on 12/29/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<String> movieImages = new ArrayList<>();
    List<String> movieNames = new ArrayList<>();
    List<String> voteAverage = new ArrayList<>();
    List<String> releaseDate = new ArrayList<>();
    List<String> overviews = new ArrayList<>();
    Context context;
    private static final String TAG = "MovieAdapter";
    private final MovieOnClickHandler movieOnClickHandler;

    public MovieAdapter(Context context,MovieOnClickHandler onClickHandler){
        this.context = context;
        this.movieOnClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout,parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d(TAG,"inside-onBindViewHolder");
        Picasso.with(context)
                .load(movieImages.get(position))
                .error(R.drawable.errorimage)
                .placeholder(R.drawable.placeholder)
                .into(holder.mPosterImages);
        holder.mMovieName.setText(movieNames.get(position));
    }

    @Override
    public int getItemCount() {
        return movieImages.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       // @BindView(R.id.tv_movieName)
        TextView mMovieName;
      //  @BindView(R.id.imageView_widget)
        ImageView mPosterImages;
       // @BindView(R.id.card_view)
        CardView mparentLayout;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieName = itemView.findViewById(R.id.tv_movieName);
            mPosterImages = itemView.findViewById(R.id.imageView_widget);
            mparentLayout = itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String posterPath = movieImages.get(adapterPosition);
            String title = movieNames.get(adapterPosition);
            String rDate = releaseDate.get(adapterPosition);
            String voteAvg = voteAverage.get(adapterPosition);
            String overview = overviews.get(adapterPosition);
            movieOnClickHandler.onClick(posterPath,title,rDate,voteAvg,overview);
        }
    }

    public interface MovieOnClickHandler{
        public void onClick(String posterPath,String title,String releaseDate,String voteAvg,String overview);
    }

    public void setMoviesData(Movie movie){
        movieNames = movie.getOriginal_title();
        movieImages = movie.getPoster_path();
        releaseDate = movie.getReleaseDate();
        voteAverage = movie.getVoteAverage();
        overviews = movie.getPlotSynopsis();
        notifyDataSetChanged();
    }
}
