package markeliny.ernesto.testappstud2016.view.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.control.IActivityController;
import markeliny.ernesto.testappstud2016.control.RockstarsSingleton;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.model.adapter.RockStarsRecyclerViewAdapter;
import markeliny.ernesto.testappstud2016.view.FragmentView;

/**
 * Created by Neness on 22/08/2016.
 */
public class RockstarsFragment extends Fragment implements SearchView.OnQueryTextListener,
        FragmentView{

    protected Context mContext;
    protected RecyclerView mRecyclerView;
    protected List<Rockstar> mRockstarList;
    protected IActivityController mController;
    private RockStarsRecyclerViewAdapter mAdapter;

    public RockstarsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.rockstars_fragment, container, false);
        //Recuperer le context auquel appartient ce fragment
        mContext = getContext();
        mController = (IActivityController) mContext;
        //Register to the controller: the activity
        mController.registerView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRockstarList = mController.getRockStarListFromModel();
        mAdapter = new RockStarsRecyclerViewAdapter(mRockstarList,this);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_rockstar_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.id_action_search:
                SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
                searchView.setOnQueryTextListener(this);
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        //Do something when expanded
                        return true; //Return true to expand action view
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        //Do something when Collapsed
                        mAdapter.updateAdaptedList(mRockstarList);
                        return true;// Return true to collapse action view
                    }
                });
                return true;
            case R.id.id_refreshmenu:
                LayoutInflater inflater =
                        (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //inflate the image image view refresh
                ImageView iv = (ImageView) inflater.inflate(R.layout.imageview_refresh,null);
                Animation rotate = AnimationUtils.loadAnimation(mContext,R.anim.rotate_refresh);
                rotate.setRepeatCount(Animation.INFINITE);
                iv.startAnimation(rotate);
                item.setActionView(iv);
                new RefreshList(item).execute();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Rockstar> filteredList = filter(mRockstarList, newText);
        mAdapter.updateAdaptedList(filteredList);
        return true;
    }

    /* The filtre is used on the firstname and lastname but not on status
     *
     * @param rockstarList
     * @param newText
     * @return
     */
    private List<Rockstar> filter(List<Rockstar> rockstarList, String newText) {
        newText = newText.toLowerCase();
        List<Rockstar> filteredList = new ArrayList<>();
        for (Rockstar r: rockstarList){
            String text = r.toString().toLowerCase();
            if (text.contains(newText)){
                filteredList.add(r);
            }
        }
        return filteredList;
    }

    @Override
    public void updateFragmentView(List<Rockstar> rockstars) {
        mRockstarList = rockstars;
        mAdapter.updateAdaptedList(mRockstarList);
    }

    @Override
    public IActivityController getController() {
        return mController;
    }

    private class RefreshList extends AsyncTask<Void, Void, Void> {

        private MenuItem mItem;

        public RefreshList(MenuItem item) {
            mItem = item;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                RockstarsSingleton.getInstance().download();
            } catch (IOException e) {
                e.printStackTrace();
                ///TODO
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (mItem.getActionView() != null){
                mController.refresh();
                //Remove the animation
                mItem.getActionView().clearAnimation();
                mItem.setActionView(null);
            }
        }
    }
}