package mars_williams.popcorn.Presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import icepick.Icepick;
import icepick.State;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mars_williams.popcorn.API.MovieDatabaseApiClient;
import mars_williams.popcorn.Activities.MainActivity;
import mars_williams.popcorn.Bundlers.MovieBundler;
import mars_williams.popcorn.Models.Movie;
import mars_williams.popcorn.PopcornApplication;

public class MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @State(MovieBundler.class)
    ArrayList<Movie> filmList;

    @Inject
    MovieDatabaseApiClient movieDatabaseApiClient;

    private MainActivity activity;

    public MainPresenter(Bundle savedState, MainActivity activity) {
        Icepick.restoreInstanceState(this, savedState);

        this.activity = activity;
        PopcornApplication application = (PopcornApplication) activity.getApplication();
        application.getAppComponent().inject(this);

        if(null == filmList) {
            fetchMovies();
        } else {
            activity.showMovies(filmList);
        }
    }

    public void onSave(@NonNull Bundle state) {
        Icepick.saveInstanceState(this, state);
    }

    public void fetchMovies() {
        movieDatabaseApiClient.getMoviesRx(MovieDatabaseApiClient.MOVIE_API_KEY, 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(moviesResponseResponse -> {

                    if (moviesResponseResponse.isSuccessful()) {
                        filmList = new ArrayList<>(moviesResponseResponse.body().getMovies());
                        activity.showMovies(filmList);
                    } else {
                        activity.showError();
                    }

                }, throwable -> {

                    Log.e(TAG, throwable.getMessage(), throwable);

                });
    }
}

