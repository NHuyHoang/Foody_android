package hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.LocationCollection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.R;



public class LocationCollectionFrag extends Fragment implements TabHost.OnTabChangeListener{
    private FragmentTabHost mTabHost;
    ArrayList<Fragment> fr = new ArrayList<Fragment>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_collection,container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);

        initTabHost();

        return rootView;
    }

    private void initTabHost() {
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Fragment1").setIndicator("Của bạn"),
                YourLocationCollection.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Fragment2").setIndicator("Xem nhiều"),
                MostRecent.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Tab3").setIndicator("Nổi bật"),
                Populate.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Fragment4").setIndicator("Đã lưu"),
                Saved.class, null);


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