package hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhat.Tab3;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.DefaultScreen;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.MyApplication;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Fragment ở đâu

public class FragEatWhere extends Fragment implements TabHost.OnTabChangeListener {
    public static FragmentTabHost mTabHost;
    int oldposition = -1;
    ArrayList<Fragment> fr = new ArrayList<Fragment>();
    int t = 1;

    //Mandatory Constructor
    public FragEatWhere() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_eatwhere,container, false);



        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);

        initTabHost();



        return rootView;
    }

    //Hàm cài đặt tabhost
    private void initTabHost() {
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("Mới nhất"),
                DefaultScreen.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Tab2").setIndicator("Danh mục"),
               Tab2.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("Tab3").setIndicator("Tab3"),
                Tab3.class, null);



        //Set toàn bộ các tabwidget màu trắng
        for(int i=0; i < mTabHost.getTabWidget().getChildCount();i++)
        {
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.systemWhite);
        }

        /*mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#eeeeee"));*/

        mTabHost.getTabWidget().getChildAt(0).setOnClickListener(new TabwidgetOnClickListener(0));
        mTabHost.getTabWidget().getChildAt(1).setOnClickListener(new TabwidgetOnClickListener(1));
        mTabHost.getTabWidget().getChildAt(2).setOnClickListener(new TabwidgetOnClickListener(2));
        /*mTabHost.setOnTabChangedListener(this);*/

        String s = ((MyApplication)getActivity().getApplication()).getCity();
        TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv.setText(s);
        tv.setAllCaps(false);
        tv = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv.setAllCaps(false);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv = (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv.setAllCaps(false);
    }

    @Override
    public void onTabChanged(String tabId) {

    }

    //Event khi nhấn các tabwidget
    private class TabwidgetOnClickListener implements View.OnClickListener{

        int position;

        public TabwidgetOnClickListener(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            //Nếu nhấn lại tabwidget cũ
            if(position == oldposition)
                t*=-1;
            else t = -1;
            oldposition = position;
            Fragment MainFragment = new Fragment();
            //Các fragment 1 2 3 tương ứng với vị trí của tabwidget 0 1 2
            switch (position){
                case(0):
                    MainFragment = new Tab1();
                    break;
                case(1):
                    MainFragment = new Tab2();
                    break;
                case(2):
                    MainFragment = new Tab3();
                    break;
            }
            //Set toàn bộ các tabwidget màu trắng
            for(int i=0; i < mTabHost.getTabWidget().getChildCount();i++)
            {
                mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.systemWhite);
            }
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            //Nếu trạng thái = 1 thì hiển thị fragment mặc định
            //đổi màu tabwidget
            if(t==1){
                DefaultScreen defaultScreen = new DefaultScreen();
                transaction.replace(R.id.realtabcontent, defaultScreen);
                mTabHost.getTabWidget().getChildAt(position).setBackgroundResource(R.color.systemWhite);
            }
            //Nếu trạng thái = -1 thì hiển thị fragment chính của tabwidget đó
            //đổi màu tabwidget
            else{
                transaction.replace(R.id.realtabcontent, MainFragment);
                mTabHost.getTabWidget().getChildAt(position).setBackgroundResource(R.color.systemGray);
            }

            transaction.addToBackStack(null);
            transaction.commit();
            getChildFragmentManager().executePendingTransactions();

            mTabHost.setCurrentTab(position);
        }
    }
}
