package markeliny.ernesto.testappstud2016.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.RockstarsSingleton;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.model.Rockstars;
import markeliny.ernesto.testappstud2016.model.adapter.RockStarsRecyclerViewAdapter;

/**
 * Created by Neness on 22/08/2016.
 */
public class RockstarsFragment extends Fragment implements IFragmentCallBack{

    private Context mContext;

    private List<Rockstar> rockstarList;

    public RockstarsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rockstarList = RockstarsSingleton.getInstance().getRockStarsList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout ll;
        RecyclerView rv;
        ll = (LinearLayout) inflater.inflate(R.layout.rockstars_fragment, container, false);
        rv = (RecyclerView) ll.findViewById(R.id.id_recyclerView_rockstars);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(new RockStarsRecyclerViewAdapter(rockstarList));
        return ll;
    }


    @Override
    public void update(List<Rockstar> aRockStarList) {
        rockstarList = aRockStarList;
    }
}
