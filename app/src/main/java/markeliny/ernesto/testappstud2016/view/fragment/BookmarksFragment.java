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
import markeliny.ernesto.testappstud2016.control.RockstarsBookmarksController;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.adapter.BookMarksRecyclerViewAdapter;
import markeliny.ernesto.testappstud2016.view.RockstarsBookmarksView;


/**
 * Created by Neness on 22/08/2016.
 */
public class BookmarksFragment extends Fragment implements RockstarsBookmarksView {

    protected Context mContext;
    protected RecyclerView mRecyclerView;
    protected List<Rockstar> mRockstarList;
    protected RockstarsBookmarksController mController;
    private BookMarksRecyclerViewAdapter mAdapter;

    public BookmarksFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        mController = (RockstarsBookmarksController) mContext;
        mController.registerView(this);
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.bookmarks_fragment, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRockstarList = mController.getRockStarListFromModel();
        mAdapter = new BookMarksRecyclerViewAdapter(this.getBookMarks(), mController);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    @Override
    public void updateRockstarsBookmarksView(List<Rockstar> rockstars) {
        mRockstarList = rockstars;
        mAdapter.updateAdaptedList(this.getBookMarks());
    }

    private List<Rockstar> getBookMarks(){
        List<Rockstar> bookmarks = new ArrayList<>();
        for(Rockstar r: mRockstarList){
            if (r.isBookmark()){
                bookmarks.add(r);
            }
        }
        return bookmarks;
    }
}
