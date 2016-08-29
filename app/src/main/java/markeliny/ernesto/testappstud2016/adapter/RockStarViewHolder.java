package markeliny.ernesto.testappstud2016.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.RockstarsBookmarksController;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 23/08/2016.
 */
public class RockStarViewHolder extends RecyclerView.ViewHolder {

    private Rockstar mRockStar;
    private RockstarsBookmarksController mController;

    private ImageView imageView;
    private TextView fullNameView;
    private TextView statusView;
    private CheckBox checkBox;

    public RockStarViewHolder(View itemView, RockstarsBookmarksController ctrl) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.id_img_view);
        fullNameView = (TextView) itemView.findViewById(R.id.id_text_name);
        statusView = (TextView) itemView.findViewById(R.id.id_text_status);
        checkBox = (CheckBox) itemView.findViewById(R.id.id_checkbox);
        mController = ctrl;
        this.setOnClickListener();
    }

    private void setOnClickListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.rockStarImageClicked(mRockStar);
            }
        });
        statusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.rockStarStatusClicked(mRockStar);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.rockStarCheckBoxCliked(mRockStar);
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
