package lxfeng.recyclerviewproject.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import lxfeng.recyclerviewproject.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private ArrayList<Fragment> mFragment = new ArrayList<Fragment>();
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    String[] mTitle = {"Grid","List","Staggered"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pageTabStrip);
        mPagerTabStrip.setTabIndicatorColorResource(R.color.colorPrimary);

        mFragment.add(GridFragment.newInstance());
        mFragment.add(ListFragment.newInstance());
        mFragment.add(StaggeredFragment.newInstance());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }

}
