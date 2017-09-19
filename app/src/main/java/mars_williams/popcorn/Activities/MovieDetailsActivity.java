package mars_williams.popcorn.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import mars_williams.popcorn.API.MovieDatabaseApiClient;
import mars_williams.popcorn.API.Trailer;
import mars_williams.popcorn.API.TrailerResponse;
import mars_williams.popcorn.Models.Movie;
import mars_williams.popcorn.PopcornApplication;
import mars_williams.popcorn.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "MOVIE";
    private static final String YOUTUBE_KEY = "AIzaSyDKNbjeQHUgdqOcekntKtrYZw6q7Ek4ZOg";

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageViewContainer)
    LinearLayout imageViewContainer;

    @Inject
    MovieDatabaseApiClient movieApiClient;

    YouTubePlayerFragment videoFr;

    private YouTubePlayer youTubePlayer;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);
        ((PopcornApplication) getApplication()).getAppComponent().inject(this);

        movie = Parcels.unwrap(getIntent().getExtras().getParcelable(EXTRA_MOVIE));
        videoFr = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment);

        if (movie.getIsPopular()) { setLayout(); }

        initGui();
    }

    private void setLayout() {
        RelativeLayout.LayoutParams playerParams =
                (RelativeLayout.LayoutParams) videoFr.getView().getLayoutParams();
        playerParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        playerParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
        imageViewContainer.setVisibility(View.GONE);
    }

    private void initGui() {

        title.setText(movie.getTitle());
        ratingBar.setRating(movie.getRating());
        releaseDate.setText(movie.getFormattedReleaseDate());
        description.setText(movie.getDescription());

        YouTubePlayer.OnInitializedListener videoHandler = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                youTubePlayer = player;
                if (movie.getIsPopular()) {
                    player.setFullscreen(true);
                }
                fetchTrailer();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult res) {

            }
        };

        videoFr.initialize(YOUTUBE_KEY, videoHandler);
    }

    private void showImage() {
        Picasso.with(this)
                .load(movie.getFullBackgropImageURL())
                .transform(new RoundedCornersTransformation(5, 0))
                .into(imageView);
    }

    private void fetchTrailer() {
        movieApiClient.getTrailer(movie.getId(), MovieDatabaseApiClient.MOVIE_API_KEY, 1).enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    List<Trailer> trailers = response.body().getTrailers();
                    if (!trailers.isEmpty() && null != videoFr.getView()) {
                        youTubePlayer.cueVideo(trailers.get(0).getSource());
                        return;
                    }
                }

                showImage();
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                showImage();
            }
        });
    }
}

