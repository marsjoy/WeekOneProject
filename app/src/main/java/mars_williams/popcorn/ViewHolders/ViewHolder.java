package mars_williams.popcorn.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import mars_williams.popcorn.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.rootView)
    public View rootView;

    public ViewHolder(View itemView) {
        super(itemView);
    }
}
