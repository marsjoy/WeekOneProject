package mars_williams.popcorn.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mars_williams.popcorn.Models.Movie;

public class MoviesResponse {

    private int page;

    @SerializedName("results")
    private List<Movie> films;

    public MoviesResponse() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return films;
    }

    public void setMovies(List<Movie> films) {
        this.films = films;
    }
}
