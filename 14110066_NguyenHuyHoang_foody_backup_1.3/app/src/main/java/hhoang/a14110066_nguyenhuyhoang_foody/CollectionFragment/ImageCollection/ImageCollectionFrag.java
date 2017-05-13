package hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.ImageCollection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.LocationCollection.MostRecent;
import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.LocationCollection.Populate;
import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.LocationCollection.Saved;
import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.LocationCollection.YourLocationCollection;
import hhoang.a14110066_nguyenhuyhoang_foody.R;


public class ImageCollectionFrag extends Fragment implements TabHost.OnTabChangeListener{
    private FragmentTabHost mTabHost;
    ArrayList<Fragment> fr = new ArrayList<Fragment>();
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.image_collection, container, false);
        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        initTabHost();
        return rootView;
    }
    private void initTabHost() {
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Fragment1").setIndicator("Của bạn"),
                YourImg.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Fragment2").setIndicator("Xem nhiều"),
                MostSeen.class, null);


        //Set toàn bộ các tabwidget màu trắng
        for(int i=0; i < mTabHost.getTabWidget().getChildCount();i++)
        {
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.systemWhite);
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(10);
        }
        mTabHost.setCurrentTab(1);
        View v = mTabHost.getTabWidget().getChildAt(1);
        TextView tv = (TextView)v.findViewById(android.R.id.title);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTabHost.setOnTabChangedListener(this);
    }
    @Override
    public void onTabChanged(String tabId) {
        int tab = mTabHost.getCurrentTab();
        for(int i =0 ;i<mTabHost.getTabWidget().getTabCount();i++){
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(android.R.color.black));
        }
        TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(tab).findViewById(android.R.id.title);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
}
