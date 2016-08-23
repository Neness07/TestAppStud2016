package markeliny.ernesto.testappstud2016.control;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstars;
import markeliny.ernesto.testappstud2016.model.adapter.MyPagerAdapter;
import markeliny.ernesto.testappstud2016.view.IObserver;
import markeliny.ernesto.testappstud2016.view.RockstarsChangedEvent;
import markeliny.ernesto.testappstud2016.view.fragment.IFragmentCallBack;

public class MainActivity extends AppCompatActivity implements IObserver{

    private Rockstars rockstarsModel;

    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private IFragmentCallBack[] mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Model init
        rockstarsModel = new Rockstars(RockstarsSingleton.getInstance().getRockStarsList());
        rockstarsModel.addAnObserver(this);

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

        mFragments = new IFragmentCallBack[2];
        mFragments[0] = (IFragmentCallBack) mPagerAdapter.getItem(0);
        mFragments[1] = (IFragmentCallBack) mPagerAdapter.getItem(1);
        mFragments[0].update(rockstarsModel.getRockstars());
        mFragments[1].update(rockstarsModel.getRockstars());
    }

    @Override
    public void rockstarListHasChanged(RockstarsChangedEvent evt) {
        rockstarsModel = new Rockstars(evt.getNewListRockstar());
        mFragments[0].update(rockstarsModel.getRockstars());
        mFragments[1].update(rockstarsModel.getRockstars());
    }
}
