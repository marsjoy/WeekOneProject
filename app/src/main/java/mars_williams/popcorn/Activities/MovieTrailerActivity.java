package mars_williams.popcorn.Activities;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import mars_williams.popcorn.API.MovieDatabaseApiClient;
import mars_williams.popcorn.API.Trailer;
import mars_williams.popcorn.API.TrailerResponse;
import mars_williams.popcorn.Models.Movie;
import mars_williams.popcorn.PopcornApplication;
import mars_williams.popcorn.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    public static final String EXTRA_MOVIE = "MOVIE";
    private static final String YOUTUBE_KEY = "AIzaSyDKNbjeQHUgdqOcekntKtrYZw6q7Ek4ZOg";

    private YouTubePlayer youTubePlayer;
    private Movie movie;

    YouTubePlayerView youTubePlayerView;

    @Inject
    MovieDatabaseApiClient movieApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        ButterKnife.bind(this);
        ((PopcornApplication) getApplication()).getAppComponent().inject(this);

        movie = Parcels.unwrap(getIntent().getExtras().getParcelable(EXTRA_MOVIE));
        System.out.print(movie);
        youTubePlayerView = findViewById(R.id.player);

        initGui();
    }

    private void initGui() {

        YouTubePlayer.OnInitializedListener videoHandler = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                youTubePlayer = player;
                player.setFullscreen(true);
                fetchTrailer();
                player.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult res) {

            }
        };

        youTubePlayerView.initialize(YOUTUBE_KEY, videoHandler);
    }

    private void fetchTrailer() {
        System.out.print(movie.getId());
        movieApiClient.getTrailer(movie.getId(), MovieDatabaseApiClient.MOVIE_API_KEY, 1).enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    List<Trailer> trailers = response.body().getTrailers();
                    if (!trailers.isEmpty() && null != youTubePlayerView) {
                        youTubePlayer.loadVideo(trailers.get(0).getSource());
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

            }
        });
    }
}