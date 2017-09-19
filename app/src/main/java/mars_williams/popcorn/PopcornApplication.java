package mars_williams.popcorn;

import android.app.Application;

import mars_williams.popcorn.Components.AppComponent;
import mars_williams.popcorn.Components.DaggerAppComponent;
import mars_williams.popcorn.Modules.CommonModule;

public class PopcornApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .commonModule(new CommonModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}