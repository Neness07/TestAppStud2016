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
public class BookMarksRecyclerViewAdapter extends RecyclerView.Adapter<BookMarkVIewHolder> {

    List<Rockstar> bookMarks;

    public BookMarksRecyclerViewAdapter(List<Rockstar> bookMarks) {
        this.bookMarks = bookMarks;
    }

    @Override
    public BookMarkVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a new view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmarks_item,parent,false);
        return new BookMarkVIewHolder(cv);
    }

    @Override
    public void onBindViewHolder(BookMarkVIewHolder holder, int position) {
        holder.getImageView().setImageBitmap(bookMarks.get(position).getPhoto());
        holder.getFullName().setText(bookMarks.get(position).toString());
        holder.getStatus().setText(bookMarks.get(position).getStatus());
        //BOUTON SUPPRESSION
    }

    @Override
    public int getItemCount() {
        return bookMarks.size();
    }
}
