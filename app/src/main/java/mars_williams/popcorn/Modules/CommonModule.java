package mars_williams.popcorn.Modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mars_williams.popcorn.API.MovieDatabaseApiClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by mars_williams on 9/18/17.
 */

@Module
public class CommonModule {

    @Provides
    @Singleton
    Retrofit getMovieRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    MovieDatabaseApiClient getMovieDatabaseApiClient(Retrofit retrofit) {
        return retrofit.create(MovieDatabaseApiClient.class);
    }
}
