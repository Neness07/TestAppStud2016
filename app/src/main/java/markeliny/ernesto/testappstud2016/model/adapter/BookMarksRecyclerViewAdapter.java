package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.view.FragmentView;

/**
 * Created by Neness on 23/08/2016.
 */
public class BookMarksRecyclerViewAdapter extends RecyclerView.Adapter<BookMarkVIewHolder> {

    private List<Rockstar> mBookMarks;
    private final FragmentView mFragment;

    public BookMarksRecyclerViewAdapter(List<Rockstar> bookMarks, final FragmentView fv) {
        mBookMarks = bookMarks;
        mFragment = fv;
    }

    @Override
    public BookMarkVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a new view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmarks_item,parent,false);
        return new BookMarkVIewHolder(cv, mFragment);
    }

    @Override
    public void onBindViewHolder(BookMarkVIewHolder holder, int position) {
        holder.bind(mBookMarks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBookMarks.size();
    }

    public void updateAdaptedList(List<Rockstar> bookMarks) {
        mBookMarks = new ArrayList<>(bookMarks);
        //refresh the recyclerview
        notifyDataSetChanged();
    }
}
