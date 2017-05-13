package hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

public class Tab1 extends Fragment {
    ListView lv;
    View rootView;
    ArrayList<ImgTextViewModel> lvElement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment1,container,false);
        initListViewElement();
        lv = (ListView) rootView.findViewById(R.id.custom_list);
        lv.setAdapter(new Frag1ListViewCustomAdapter(getContext(),lvElement));
        return rootView;
    }


    private void initListViewElement(){
        lvElement = new ArrayList<ImgTextViewModel>();
        lvElement.add(new ImgTextViewModel("Mới nhất",R.drawable.home_ic_filter_latest));
        lvElement.add(new ImgTextViewModel("Gần tôi",R.drawable.home_ic_filter_most_near));
        lvElement.add(new ImgTextViewModel("Phổ biến",R.drawable.home_ic_filter_top_of_week));
        lvElement.add(new ImgTextViewModel("Du Khách",R.drawable.home_ic_filter_tourist));
        lvElement.add(new ImgTextViewModel("Ưu đãi E-card",R.drawable.home_ic_filter_ecard));
        lvElement.add(new ImgTextViewModel("Đặt chỗ",R.drawable.home_ic_filter_most_reservation));
        lvElement.add(new ImgTextViewModel("Ưu đãi thẻ",R.drawable.home_ic_filter_bankcard));
        lvElement.add(new ImgTextViewModel("Đặt giao hàng",R.drawable.home_ic_delivery));
    }
}