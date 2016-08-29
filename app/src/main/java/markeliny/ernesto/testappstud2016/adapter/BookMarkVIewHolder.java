package markeliny.ernesto.testappstud2016.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.RockstarsBookmarksController;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 23/08/2016.
 */
public class BookMarkVIewHolder extends RecyclerView.ViewHolder {

    private Rockstar mRockStar;
    private RockstarsBookmarksController mController;

    private ImageView imageView;
    private TextView fullNameView;
    private TextView statusView;
    private ImageButton delBtn;

    public BookMarkVIewHolder(View itemView, RockstarsBookmarksController ctrl) {
        super(itemView);
        mController = ctrl;
        imageView = (ImageView) itemView.findViewById(R.id.id_img_view);
        fullNameView = (TextView) itemView.findViewById(R.id.id_text_name);
        statusView = (TextView) itemView.findViewById(R.id.id_text_status);
        delBtn = (ImageButton) itemView.findViewById(R.id.id_imageButton);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.deleteFromBookMarks(mRockStar);
            }
        });
    }

    public void bind(Rockstar r){
        mRockStar = r;
        imageView.setImageBitmap(mRockStar.getPhoto());
        fullNameView.setText(mRockStar.toString());
        statusView.setText(mRockStar.getStatus());
    }
}
