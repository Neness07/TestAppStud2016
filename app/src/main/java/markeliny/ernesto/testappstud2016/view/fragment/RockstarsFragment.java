package markeliny.ernesto.testappstud2016.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import markeliny.ernesto.testappstud2016.R;

/**
 * Created by Neness on 22/08/2016.
 */
public class RockstarsFragment extends Fragment {

    private LinearLayout ll;

    public RockstarsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ll = (LinearLayout) inflater.inflate(R.layout.rockstars_fragment, container, false);
        ll.findViewById(R.id.id_recyclerView_rockstars);
        //Herre we can instantiate an adapter for the rockstars list.
        //To look after the refresh button and the searh bar
        return ll;
    }
}
