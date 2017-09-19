package mars_williams.popcorn.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mars_williams.popcorn.R;

public class PopularMovieViewHolder extends ViewHolder {

    @BindView(R.id.imageView)
    public ImageView imageView;

    public PopularMovieViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }
}

