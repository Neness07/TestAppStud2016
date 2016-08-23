package markeliny.ernesto.testappstud2016.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;

/**
 * Created by Neness on 23/08/2016.
 */
public class RockStarViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    private TextView fullName;

    private TextView status;

    private CheckBox cb;


    public RockStarViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.id_img_view);
        fullName = (TextView) itemView.findViewById(R.id.id_text_name);
        status = (TextView) itemView.findViewById(R.id.id_text_status);
        cb = (CheckBox) itemView.findViewById(R.id.id_checkbox);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getFullName() {
        return fullName;
    }

    public TextView getStatus() {
        return status;
    }

    public CheckBox getCheckBox() {
        return cb;
    }
}
