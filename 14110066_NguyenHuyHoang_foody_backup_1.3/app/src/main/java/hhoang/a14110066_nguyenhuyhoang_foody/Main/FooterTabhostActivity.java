package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.CollectionFragment;
import hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.HomeFragment;
import hhoang.a14110066_nguyenhuyhoang_foody.NotiFragment.NotiFragment.NotiFragment;
import hhoang.a14110066_nguyenhuyhoang_foody.R;
import hhoang.a14110066_nguyenhuyhoang_foody.SearchFragment.SearchFragment;

public class FooterTabhostActivity extends Fragment {
    private FragmentTabHost mTabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_footer_tabhost,container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);

        initTabHost();



        return rootView;
    }
    //Hàm tạo tabhost dưới đáy màn hình
    private void initTabHost() {
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        //Mỗi tab trong tabhost sẽ chỉ đến các fragment tương ứng
        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("",getResources().getDrawable(R.drawable.main_menu_home)),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Tab2").setIndicator("", getResources().getDrawable(R.drawable.main_menu_collection)),
                CollectionFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Tab3").setIndicator("", getResources().getDrawable(R.drawable.main_menu_search)),
                SearchFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Fragment4").setIndicator("", getResources().getDrawable(R.drawable.main_menu_noti)),
                NotiFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Fragment5").setIndicator("", getResources().getDrawable(R.drawable.main_menu_profile)),
                AccountFragment.class, null);

        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++){
            mTabHost.getTabWidget().getChildAt(i).setPadding(0,50,0,50);
        }

        mTabHost.getTabWidget().setDividerDrawable(null);

    }

}
