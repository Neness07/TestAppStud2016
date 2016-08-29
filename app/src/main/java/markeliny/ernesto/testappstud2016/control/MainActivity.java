package markeliny.ernesto.testappstud2016.control;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.util.UtilitiesSingleton;
import markeliny.ernesto.testappstud2016.model.MyProfile;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.model.Rockstars;
import markeliny.ernesto.testappstud2016.adapter.MyPagerAdapter;
import markeliny.ernesto.testappstud2016.view.ProfileView;
import markeliny.ernesto.testappstud2016.view.RockstarsBookmarksView;
import markeliny.ernesto.testappstud2016.view.ModelObserver;
import markeliny.ernesto.testappstud2016.view.RockstarsChangedEvent;

public class MainActivity extends AppCompatActivity implements ModelObserver, RockstarsBookmarksController {

    private Rockstars rockstarsModel;
    private List<RockstarsBookmarksView> mViews;
    private MyProfile mProfile;
    private ProfileView mProfileView;

    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Model init
        rockstarsModel = new Rockstars(UtilitiesSingleton.getInstance().getRockStarsList());
        rockstarsModel.addAnObserver(this);
        mViews = new ArrayList<>();

        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.id_collapseToolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.id_tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_dehaze_white_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_bookmark_white_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_person_white_24dp);


    }

    @Override
    protected void onResume() {
        super.onResume();
        UtilitiesSingleton.getInstance().getmDBManager().openRockstarsTable();
        UtilitiesSingleton.getInstance().getmDBManager().openProfileTable();
    }

    @Override
    protected void onPause(){
        super.onPause();
        UtilitiesSingleton.getInstance().getmDBManager()
                .insertOrUpdateRockstars(rockstarsModel.getRockstars());
        //Insert or update profile her
        UtilitiesSingleton.getInstance().getmDBManager().closeRockstarsTable();
        UtilitiesSingleton.getInstance().getmDBManager().closeProfileTable();
    }

    @Override
    public void rockstarsChanged(RockstarsChangedEvent evt) {
        rockstarsModel = new Rockstars(evt.getNewListRockstar());
        notifyAllViews();
    }

    @Override
    public List<Rockstar> getRockStarListFromModel() {
        return rockstarsModel.getRockstars();
    }

    @Override
    public void refresh() {
        List<Rockstar> newList = new ArrayList<>(UtilitiesSingleton.getInstance().getRockStarsList());
        Rockstars newModel = new Rockstars(newList);
        for (Rockstar r: rockstarsModel.getRockstars()){
            if (newModel.contains(r)){
                int index = newModel.getRockstars().indexOf(r);
                newModel.getRockstars().get(index).setBookmark(r.isBookmark());
            }
        }
        rockstarsModel.setRockstars(newModel.getRockstars());
    }

    @Override
    public void registerView(RockstarsBookmarksView fv) {
        mViews.add(fv);
    }

    @Override
    public void rockStarImageClicked(Rockstar aRockStar) {
        ///TODO
        //Do something like: Change the image or make it in full screen mode
    }

    @Override
    public void rockStarCheckBoxCliked(Rockstar aRockStar) {
        if (rockstarsModel.contains(aRockStar)){
            if (aRockStar.isBookmark()){
                rockstarsModel.removeFromBookmarks(aRockStar);
            } else {
                rockstarsModel.addToBookmarks(aRockStar);
            }
            notifyAllViews();
        }
    }

    @Override
    public void rockStarStatusClicked(Rockstar aRockStar) {
        ///TODO
        //DO something like: modify the status text
    }

    @Override
    public void deleteFromBookMarks(Rockstar aRockStar) {
        rockstarsModel.removeFromBookmarks(aRockStar);
        notifyAllViews();
    }

    private void notifyAllViews(){
        for(RockstarsBookmarksView fv: mViews){
            fv.updateRockstarsBookmarksView(rockstarsModel.getRockstars());
        }
    }
}
