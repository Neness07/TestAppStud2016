package markeliny.ernesto.testappstud2016.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import markeliny.ernesto.testappstud2016.R;


/**
 * Created by Neness on 22/08/2016.
 */
public class BookmarksFragment extends Fragment {

    private RecyclerView rv;

    public BookmarksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rv = (RecyclerView) inflater.inflate(R.layout.bookmarks_fragment, container, false);
        //Herre we can instantiate an adapter for the bookmarks list.
        return rv;
    }
}
