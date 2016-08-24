package markeliny.ernesto.testappstud2016.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.view.fragment.BookmarksFragment;
import markeliny.ernesto.testappstud2016.view.fragment.ProfileFragment;
import markeliny.ernesto.testappstud2016.view.fragment.RockstarsFragment;

/**
 * This is a view pager adapter for the swipe tabs feature
 * Here, he have only three tabs so we can use a FragmentPagerAdapter instead of
 * FragmentStatePagerAdapter which is the best for the indeterminate number of tabs.
 *
 * This adapter register also the fragments instantiated, so you can get them from everywhere you
 * have an instance of this view pager adapter(e.g: Activity) by calling {@link getItem}
 *
 * Created by Neness on 22/08/2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                mFragmentList.add(new RockstarsFragment());
                return mFragmentList.get(0);
            case 1:
                mFragmentList.add(new BookmarksFragment());
                return mFragmentList.get(1);
            case 2:
                mFragmentList.add(new ProfileFragment());
                return mFragmentList.get(2);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = "";
        switch (position){
            case 0:
                title = "rockstars";
                break;
            case 1:
                title = "bookmarks";
                break;
            case 2:
                title = "profile";
                break;
        }
        return title;
    }
}
