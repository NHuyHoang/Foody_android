package hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.ImageCollection.ImageCollectionFrag;
import hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.LocationCollection.LocationCollectionFrag;
import hhoang.a14110066_nguyenhuyhoang_foody.Adapter.CustomPagerAdapter;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Fragment bộ sưu tập thuộc tabhost ở dưới màn hình

public class CollectionFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private RadioGroup radioGroup1;
    ArrayList<Fragment> fr = new ArrayList<Fragment>();
    ViewPager pager;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.collective_fragment, container, false);
        initPager();
        radioGroup1 = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //Khi click 1 trong 2 nút trong radioGroup button sẽ trả ra Pager tương ứng
            //với nút đã chọn

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.localeCollection:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.imageCollection:
                        pager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });
        return rootView;
    }

    //Hàm tạo 2 Pager gồm Pager Bộ sưu tập địa điểm và Pager bộ sưu tập hình ảnh
    private void initPager() {
        pager = (ViewPager) rootView.findViewById(R.id.viewPager);
        Fragment frag1 =  new LocationCollectionFrag();
        Fragment frag2 =  new ImageCollectionFrag();
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
        //Lướt trang thì thay đổi trạng thái button
        RadioButton rdb1 = (RadioButton) rootView.findViewById(R.id.localeCollection);
        RadioButton rdb2 = (RadioButton) rootView.findViewById(R.id.imageCollection);
        if(position==1){
            radioGroup1.check(R.id.imageCollection);
        }
        else radioGroup1.check(R.id.localeCollection);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
