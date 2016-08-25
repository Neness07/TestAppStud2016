package markeliny.ernesto.testappstud2016.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.id_action_search:
                ///TODO validate profile
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}
