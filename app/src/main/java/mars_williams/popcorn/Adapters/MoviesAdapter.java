package mars_williams.popcorn.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import mars_williams.popcorn.Activities.MovieDetailsActivity;
import mars_williams.popcorn.Activities.MovieTrailerActivity;
import mars_williams.popcorn.Models.Movie;
import mars_williams.popcorn.R;
import mars_williams.popcorn.ViewHolders.MovieViewHolder;
import mars_williams.popcorn.ViewHolders.PopularMovieViewHolder;
import mars_williams.popcorn.ViewHolders.ViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<ViewHolder> {

    private int VIEW_TYPE_MOVIE = 0;
    private int VIEW_TYPE_POPULAR_MOVIE = 1;

    private final List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_POPULAR_MOVIE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item_movie_popular, parent, false);
            return new PopularMovieViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        Context context = viewHolder.rootView.getContext();
        Movie movie = movies.get(pos);
        movie.setIsPopular(getItemViewType(pos) != VIEW_TYPE_MOVIE);
        if (!movie.getIsPopular()) {
            MovieViewHolder vh = (MovieViewHolder) viewHolder;
            vh.title.setText(movie.getTitle());
            vh.desc.setText(movie.getDescription());
            vh.releaseDate.setText(movie.getFormattedReleaseDate());
            vh.ratingBar.setRating(movie.getRating());

            String imageURL = isLandscape(context) ? movie.getFullBackgropImageURL() : movie.getFullPosterImageURL();
            fetchRoundedImage(context, vh.imageView, imageURL);
        } else {
            PopularMovieViewHolder vh = (PopularMovieViewHolder) viewHolder;
            fetchRoundedImage(context, vh.imageView, movie.getFullBackgropImageURL());
        }

        viewHolder.rootView.setOnClickListener(view -> openMovieDetails(context, movie));
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position).getRating() > 5.0d ? VIEW_TYPE_POPULAR_MOVIE : VIEW_TYPE_MOVIE;
    }

    private boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void fetchRoundedImage(Context context, ImageView imageView, String imageURL) {
        RequestCreator requestCreator = Picasso.with(context)
                .load(imageURL)
                .placeholder(R.drawable.ic_movies)
                .error(R.drawable.ic_movies).transform(new RoundedCornersTransformation(10, 10));
        requestCreator.into(imageView);
    }

    private void openMovieDetails(Context context, Movie movie) {
        if (!movie.getIsPopular()) {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, Parcels.wrap(movie));
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, MovieTrailerActivity.class);
            intent.putExtra(MovieTrailerActivity.EXTRA_MOVIE, Parcels.wrap(movie));
            context.startActivity(intent);
        }
    }
}

