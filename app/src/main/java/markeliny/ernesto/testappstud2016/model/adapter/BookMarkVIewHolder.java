package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.view.FragmentView;

/**
 * Created by Neness on 23/08/2016.
 */
public class BookMarkVIewHolder extends RecyclerView.ViewHolder {

    private final FragmentView mFragment;
    private Rockstar mRockStar;

    private ImageView imageView;
    private TextView fullNameView;
    private TextView statusView;
    private ImageButton delBtn;

    public BookMarkVIewHolder(View itemView, final FragmentView fv) {
        super(itemView);
        mFragment = fv;

        imageView = (ImageView) itemView.findViewById(R.id.id_img_view);
        fullNameView = (TextView) itemView.findViewById(R.id.id_text_name);
        statusView = (TextView) itemView.findViewById(R.id.id_text_status);
        delBtn = (ImageButton) itemView.findViewById(R.id.id_imageButton);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.getController().deleteFromBookMarks(mRockStar);
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
