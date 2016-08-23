package markeliny.ernesto.testappstud2016.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.IControllerCallBack;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 23/08/2016.
 */
public class RockStarsRecyclerViewAdapter extends RecyclerView.Adapter<RockStarViewHolder> {

    List<Rockstar> rockstars;

    IControllerCallBack mCtrl;

    public RockStarsRecyclerViewAdapter(List<Rockstar> rockstars, IControllerCallBack ctrl) {
        this.rockstars = rockstars;
        mCtrl = ctrl;
    }

    @Override
    public RockStarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rockstars_item,parent,false);
        return new RockStarViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(RockStarViewHolder holder, int position) {
        holder.getImageView().setImageBitmap(rockstars.get(position).getPhoto());
        holder.getFullName().setText(rockstars.get(position).toString());
        holder.getStatus().setText(rockstars.get(position).getStatus());
        holder.getCheckBox().setChecked(rockstars.get(position).isBookmark());
        holder.getCheckBox().setTag(rockstars.get(position));
        //Listener on the checkbox: ==> add to bookmarks
        holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                Rockstar rockstar = (Rockstar) cb.getTag();
                mCtrl.onRockStarSelected(rockstar,cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return rockstars.size();
    }
}
