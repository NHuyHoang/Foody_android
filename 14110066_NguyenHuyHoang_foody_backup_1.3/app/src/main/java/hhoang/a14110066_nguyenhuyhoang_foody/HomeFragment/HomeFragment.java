package hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhat.FragEatWhat;
import hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhere.FragEatWhere;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.CustomPagerAdapter;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Fragment hiển thị trang chính
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{
    //Group button ăn gì ở đâu
    private RadioGroup radioGroup1;
    TabHost tabHost;
    ArrayList<Fragment> fr = new ArrayList<Fragment>();
    ViewPager pager;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.home_fragment,container, false);
        initPager();
        //Groupbutton ăn gì ở đâu
        radioGroup1 = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            //Khi click 1 trong 2 nút trong radioGroup button sẽ trả ra Pager tương ứng
            //với nút đã chọn

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case R.id.EatWhat:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.EatWhere:
                        pager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });
        return rootView;
    }


    //Hàm tạo 2 Pager gồm Pager ăn gì và Pager ở đâu
    private void initPager() {
        pager = (ViewPager) rootView.findViewById(R.id.viewPager);
        Fragment frag1 =  new FragEatWhat();
        Fragment frag2 =  new FragEatWhere();
        fr.add(frag1);
        fr.add(frag2);
        CustomPagerAdapter myFrAdapt = new CustomPagerAdapter(getChildFragmentManager(),fr);
        pager.setAdapter(myFrAdapt);
        pager.setOnPageChangeListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        //Nếu chuyển qua pager khác thì chuyển groub radio button ăn gì ở đâu
        RadioButton rdb1 = (RadioButton) rootView.findViewById(R.id.EatWhat);
        RadioButton rdb2 = (RadioButton) rootView.findViewById(R.id.EatWhere);
        if(position==1){
            radioGroup1.check(R.id.EatWhere);
        }
        else radioGroup1.check(R.id.EatWhat);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
