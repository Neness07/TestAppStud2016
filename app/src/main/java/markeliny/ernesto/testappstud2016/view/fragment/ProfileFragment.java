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
public class ProfileFragment extends Fragment {

    LinearLayout ll;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ll = (LinearLayout) inflater.inflate(R.layout.profile_fragment, container, false);

        return ll;
    }
}
