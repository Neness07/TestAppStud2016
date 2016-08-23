package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 23/08/2016.
 */
public class RockStarsRecyclerViewAdapter extends RecyclerView.Adapter<RockStarViewHolder> {

    List<Rockstar> rockstars;

    public RockStarsRecyclerViewAdapter(List<Rockstar> rockstars) {
        this.rockstars = rockstars;
    }

    @Override
    public RockStarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a new view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rockstars_item,parent,false);
        return new RockStarViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(RockStarViewHolder holder, int position) {
        holder.getImageView().setImageBitmap(rockstars.get(position).getPhoto());
        holder.getFullName().setText(rockstars.get(position).toString());
        holder.getStatus().setText(rockstars.get(position).getStatus());
        //CHECK BOX
    }

    @Override
    public int getItemCount() {
        return rockstars.size();
    }
}
