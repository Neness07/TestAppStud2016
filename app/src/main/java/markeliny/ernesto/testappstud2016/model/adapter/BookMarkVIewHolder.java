package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;

/**
 * Created by Neness on 23/08/2016.
 */
public class BookMarkVIewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    private TextView fullName;

    private TextView status;

    private Button delBtn;

    public BookMarkVIewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.id_img_view);
        fullName = (TextView) itemView.findViewById(R.id.id_text_name);
        status = (TextView) itemView.findViewById(R.id.id_text_status);
        //ICI POUR le bouton poubelle(suppression) de l'item de bookmarks
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

    public Button getDelBtn() {
        return delBtn;
    }
}
