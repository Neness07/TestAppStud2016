package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.view.FragmentView;

/**
 * Created by Neness on 23/08/2016.
 */
public class RockStarViewHolder extends RecyclerView.ViewHolder {

    private FragmentView mFragment;
    private Rockstar mRockStar;

    private ImageView imageView;
    private TextView fullNameView;
    private TextView statusView;
    private CheckBox checkBox;

    public RockStarViewHolder(View itemView, final FragmentView fv) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.id_img_view);
        fullNameView = (TextView) itemView.findViewById(R.id.id_text_name);
        statusView = (TextView) itemView.findViewById(R.id.id_text_status);
        checkBox = (CheckBox) itemView.findViewById(R.id.id_checkbox);
        mFragment = fv;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.getController().rockStarImageClicked(mRockStar);
            }
        });
        statusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.getController().rockStarStatusClicked(mRockStar);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.getController().rockStarCheckBoxCliked(mRockStar);
            }
        });
    }

    //bind this ViewHolder to the given rocksatr
    public void bind(Rockstar r){
        mRockStar = r;
        imageView.setImageBitmap(mRockStar.getPhoto());
        fullNameView.setText(mRockStar.toString());
        statusView.setText(mRockStar.getStatus());
        checkBox.setChecked(mRockStar.isBookmark());
    }
}
