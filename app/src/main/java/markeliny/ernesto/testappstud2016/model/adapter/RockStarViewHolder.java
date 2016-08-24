package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstar;

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

    public void bind(Rockstar r){
        imageView.setImageBitmap(r.getPhoto());
        fullName.setText(r.toString());
        status.setText(r.getStatus());
        cb.setChecked(r.isBookmark());
        cb.setTag(r);
    }

    public void setOnClickListener(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                Rockstar rockstar = (Rockstar) cb.getTag();
                //On peut faire quelque chose
                //Ex: Agrandir l'image(plein ecran)
            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                Rockstar rockstar = (Rockstar) cb.getTag();
                //On peut faire quelque chose
                //Ex: Add to or remove from bookmarks
            }
        });
    }
}
