package mars_williams.popcorn.API;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by mars_williams on 9/18/17.
 */

public interface MovieDatabaseApiClient {
    String MOVIE_API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @GET("movie/now_playing")
    Call<MoviesResponse> getMovies(@Query("api_key") String key, @Query("page") int page);

    @GET("movie/{movieId}/trailers")
    Call<TrailerResponse> getTrailer(@Path("movieId") String id, @Query("api_key") String key, @Query("page") int page);

    @GET("movie/now_playing")
    Observable<Response<MoviesResponse>> getMoviesRx(@Query("api_key") String key, @Query("page") int page);
}

