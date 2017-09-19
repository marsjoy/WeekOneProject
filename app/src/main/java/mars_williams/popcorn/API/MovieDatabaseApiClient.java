package mars_williams.popcorn.API;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDatabaseApiClient {
    String MOVIE_API_KEY = "1c6ec152b86e33aa678e1943459b758d";

    @GET("movie/now_playing")
    Call<MoviesResponse> getMovies(@Query("api_key") String key, @Query("page") int page);

    @GET("movie/{movieId}/trailers")
    Call<TrailerResponse> getTrailer(@Path("movieId") String id, @Query("api_key") String key, @Query("page") int page);

    @GET("movie/now_playing")
    Observable<Response<MoviesResponse>> getMoviesRx(@Query("api_key") String key, @Query("page") int page);
}

