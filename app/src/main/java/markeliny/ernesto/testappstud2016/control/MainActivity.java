package markeliny.ernesto.testappstud2016.control;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.model.Rockstars;
import markeliny.ernesto.testappstud2016.model.adapter.MyPagerAdapter;

public class MainActivity extends FragmentActivity {

    private Rockstars rockstarsModel;

    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Model init
        rockstarsModel = new Rockstars(RockstarsSingleton.getInstance().getRockStarsList());

        mTabLayout = (TabLayout) findViewById(R.id.id_tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mPagerAdapter.getPageTitle(2)));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


    }
}
