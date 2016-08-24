package markeliny.ernesto.testappstud2016.control;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


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

        mTabLayout = (TabLayout) findViewById(R.id.id_tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(2)));

        mTabLayout.setupWithViewPager(mViewPager);
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
        try {
            RockstarsSingleton.getInstance().download();
            rockstarsModel = new Rockstars(RockstarsSingleton.getInstance().getRockStarsList());
            //notifyAllViews();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerView(FragmentView fv) {
        mViews.add(fv);
    }

    private void notifyAllViews(){
        for(FragmentView fv: mViews){
            fv.updateFragmentView(rockstarsModel.getRockstars());
        }
    }
}
