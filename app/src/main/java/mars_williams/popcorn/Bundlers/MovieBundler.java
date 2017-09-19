package mars_williams.popcorn.Bundlers;

import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import icepick.Bundler;
import mars_williams.popcorn.Models.Movie;

public class MovieBundler implements Bundler<ArrayList<Movie>> {
    @Override
    public void put(String key, ArrayList<Movie> movies, Bundle bundle) {
        bundle.putParcelable(key, Parcels.wrap(movies));
    }

    @Override
    public ArrayList<Movie> get(String key, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(key));
    }
}
