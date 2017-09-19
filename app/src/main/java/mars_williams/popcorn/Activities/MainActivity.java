package mars_williams.popcorn.Activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import mars_williams.popcorn.Adapters.MoviesAdapter;
import mars_williams.popcorn.Models.Movie;
import mars_williams.popcorn.Presenters.MainPresenter;
import mars_williams.popcorn.R;

/**
 * Created by mars_williams on 9/18/17.
 */


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.filmListView)
    RecyclerView moviesListView;

    @BindView(R.id.swipeListViewContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Icepick.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initGui();

        this.presenter = new MainPresenter(savedInstanceState, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        presenter.onSave(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void initGui() {
        moviesListView.setLayoutManager(new LinearLayoutManager(this));
        moviesAdapter = new MoviesAdapter(movies);
        moviesListView.setAdapter(moviesAdapter);

        swipeContainer.setOnRefreshListener(() -> presenter.fetchMovies());

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.primary_tint_90,
                R.color.primary_tint_80,
                R.color.primary_tint_70,
                R.color.primary_tint_60);
    }

    public void showMovies(List<Movie> movieList) {
        swipeContainer.setRefreshing(false);

        movies.addAll(movieList);
        moviesAdapter.notifyDataSetChanged();
    }

    public void showError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show();
    }
}
