package mars_williams.popcorn.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mars_williams on 9/18/17.
 */

public class TrailerResponse {

    @SerializedName("youtube")
    List<Trailer> trailers;


    public TrailerResponse() {

    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
