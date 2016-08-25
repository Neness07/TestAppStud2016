package markeliny.ernesto.testappstud2016.control;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.model.Rockstars;
import markeliny.ernesto.testappstud2016.model.adapter.MyPagerAdapter;
import markeliny.ernesto.testappstud2016.view.FragmentView;
import markeliny.ernesto.testappstud2016.view.ModelObserver;
import markeliny.ernesto.testappstud2016.view.RockstarsChangedEvent;

public class MainActivity extends AppCompatActivity implements ModelObserver, IActivityController {

    private Rockstars rockstarsModel;
    private List<FragmentView> mViews;

    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Model init
        rockstarsModel = new Rockstars(RockstarsSingleton.getInstance().getRockStarsList());
        rockstarsModel.addAnObserver(this);

        mViews = new ArrayList<>();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.id_tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(2)));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_dehaze_white_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_bookmark_white_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_person_white_24dp);
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
        List<Rockstar> newList = new ArrayList<>(RockstarsSingleton.getInstance().getRockStarsList());
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
    public void registerView(FragmentView fv) {
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
        for(FragmentView fv: mViews){
            fv.updateFragmentView(rockstarsModel.getRockstars());
        }
    }
}
