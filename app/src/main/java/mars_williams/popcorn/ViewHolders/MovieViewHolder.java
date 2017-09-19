package mars_williams.popcorn.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mars_williams.popcorn.R;

public class MovieViewHolder extends ViewHolder {

    @BindView(R.id.title)
    public TextView title;

    @BindView(R.id.description)
    public TextView desc;

    @BindView(R.id.imageView)
    public ImageView imageView;

    @BindView(R.id.releaseDate)
    public TextView releaseDate;

    @BindView(R.id.ratingBar)
    public RatingBar ratingBar;

    public MovieViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }
}

