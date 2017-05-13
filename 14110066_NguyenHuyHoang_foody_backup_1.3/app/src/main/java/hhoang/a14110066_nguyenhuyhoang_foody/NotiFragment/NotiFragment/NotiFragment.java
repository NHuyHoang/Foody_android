package hhoang.a14110066_nguyenhuyhoang_foody.NotiFragment.NotiFragment;

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
import hhoang.a14110066_nguyenhuyhoang_foody.SearchFragment.RecentBooked;
import hhoang.a14110066_nguyenhuyhoang_foody.SearchFragment.RecentSeen;

//Fragment thông báo thuộc tabhost dưới màn hình

public class NotiFragment extends Fragment implements TabHost.OnTabChangeListener {
    View rootView;
    private FragmentTabHost mTabHost;
    ArrayList<Fragment> fr = new ArrayList<Fragment>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.noti_fragment,container, false);
        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        initTabHost();
        return rootView;
    }

    private void initTabHost() {
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Fragment1").setIndicator("Dịch vụ"),
                Services.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Fragment2").setIndicator("Của tôi"),
                MyNoti.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Tab3").setIndicator("Tin mới"),
                News.class, null);

        //Set toàn bộ các tabwidget màu trắng
        for(int i=0; i < mTabHost.getTabWidget().getChildCount();i++)
        {
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.systemWhite);
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(10);
        }
        mTabHost.setCurrentTab(2);
        View v = mTabHost.getTabWidget().getChildAt(2);
        TextView tv = (TextView)v.findViewById(android.R.id.title);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTabHost.setOnTabChangedListener(this);
    }
    @Override
    public void onTabChanged(String tabId) {
        int tab = mTabHost.getCurrentTab();
        //tab được chọn có màu đỏ còn lại có màu đen
        for(int i =0 ;i<mTabHost.getTabWidget().getTabCount();i++){
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(android.R.color.black));
        }
        TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(tab).findViewById(android.R.id.title);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
}
