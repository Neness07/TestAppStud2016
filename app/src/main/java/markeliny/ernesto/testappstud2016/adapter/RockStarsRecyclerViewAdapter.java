package markeliny.ernesto.testappstud2016.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.RockstarsBookmarksController;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 23/08/2016.
 */
public class RockStarsRecyclerViewAdapter extends RecyclerView.Adapter<RockStarViewHolder> {

    private List<Rockstar> mRockstars;

    private RockstarsBookmarksController mContoller;

    public RockStarsRecyclerViewAdapter(List<Rockstar> rockstars, RockstarsBookmarksController ctrl) {
        mRockstars = rockstars;
        mContoller = ctrl;
    }

    @Override
    public RockStarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rockstars_item,parent,false);
        return new RockStarViewHolder(cv,mContoller);
    }

    @Override
    public void onBindViewHolder(RockStarViewHolder holder, int position) {
        holder.bind(mRockstars.get(position));
    }

    @Override
    public int getItemCount() {
        return mRockstars.size();
    }

    /**
     * set a new list and refresh the recyclerview
     * @param rockstars
     */
    public void updateAdaptedList(List<Rockstar> rockstars) {
        mRockstars = new ArrayList<>(rockstars);
        //refresh the recyclerview
        notifyDataSetChanged();
    }
}
