package mars_williams.popcorn.Models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by mars_williams on 9/18/17.
 */

@Parcel
public class Movie  {

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("backdrop_path")
    String backdropPath;

    @SerializedName("title")
    String title;

    @SerializedName("overview")
    String description;

    @SerializedName("release_date")
    String releaseDate;

    @SerializedName("vote_average")
    float rating;

    @SerializedName("id")
    String id;

    @SerializedName("video")
    boolean video;

    public Movie() {
    }

    public Movie(String posterPath, String backdropPath, String title, String description, String releaseDate, float rating, String id, boolean video) {
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.id = id;
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getFormattedReleaseDate() {
        return "Release: " + releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullBackgropImageURL() {
        return "https://image.tmdb.org/t/p/w300/" + backdropPath;
    }

    public String getFullPosterImageURL() {
        return "https://image.tmdb.org/t/p/w300/" + posterPath;
    }
}

