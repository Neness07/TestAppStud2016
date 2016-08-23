package markeliny.ernesto.testappstud2016.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.RockstarsSingleton;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.view.BookMarksRecyclerViewAdapter;


/**
 * Created by Neness on 22/08/2016.
 */
public class BookmarksFragment extends Fragment implements IFragmentCallBack {

    private Context mContext;

    private List<Rockstar> rockstarList ;

    public BookmarksFragment() {}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rockstarList = RockstarsSingleton.getInstance().getRockStarsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.bookmarks_fragment, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        List<Rockstar> bookmarks = new ArrayList<>();
        for(Rockstar r: rockstarList){
            if (r.isBookmark()){
                bookmarks.add(r);
            }
        }
        mRecyclerView.setAdapter(new BookMarksRecyclerViewAdapter(bookmarks));
        return mRecyclerView;
    }

    @Override
    public void update(List<Rockstar> aRockStarList) {
        rockstarList = aRockStarList;
    }
}
