package swetha.popularmoviesapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swetha on 12/29/18.
 */

public class Movie {

    List<String> original_title= new ArrayList<>();
    List<String> poster_path= new ArrayList<>();
    List<String> releaseDate = new ArrayList<>();
    List<String> voteAverage = new ArrayList<>();
    List<String> plotSynopsis = new ArrayList<>();

    public Movie(){

    }

    public Movie(List title,List path,List releaseDate,List voteAverage,List plotSynopsis){
        this.original_title=title;
        this.poster_path=path;
        this.releaseDate=releaseDate;
        this.voteAverage=voteAverage;
        this.plotSynopsis=plotSynopsis;
    }

    public List<String> getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(List<String> original_title) {
        this.original_title = original_title;
    }

    public List<String> getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(List<String> poster_path) {
        this.poster_path = poster_path;
    }

    public List<String> getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(List<String> releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(List<String> voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<String> getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(List<String> plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }
}
