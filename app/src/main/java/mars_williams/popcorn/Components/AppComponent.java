package mars_williams.popcorn.Components;

import javax.inject.Singleton;

import dagger.Component;
import mars_williams.popcorn.Activities.MainActivity;
import mars_williams.popcorn.Activities.MovieDetailsActivity;
import mars_williams.popcorn.Activities.MovieTrailerActivity;
import mars_williams.popcorn.Modules.CommonModule;
import mars_williams.popcorn.Presenters.MainPresenter;

@Singleton
@Component(modules = CommonModule.class)
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(MovieDetailsActivity activity);

    void inject(MovieTrailerActivity activity);

    void inject(MainPresenter target);
}


