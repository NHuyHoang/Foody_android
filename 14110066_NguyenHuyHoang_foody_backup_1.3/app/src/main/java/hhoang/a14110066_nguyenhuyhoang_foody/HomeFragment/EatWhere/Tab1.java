package hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhere;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhat.Frag1ListViewCustomAdapter;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

/**
 * Created by SONY on 4/6/2017.
 */

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
        /*Button b = (Button) rootView.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication val = (MyApplication)getActivity().getApplicationContext();
                val.setVal("tp.hcm");
            }
        });*/
        return rootView;
    }


    private void initListViewElement(){
        lvElement = new ArrayList<ImgTextViewModel>();
        lvElement.add(new ImgTextViewModel("Mới nhất",R.drawable.home_ic_filter_latest));
        lvElement.add(new ImgTextViewModel("Gần tôi",R.drawable.home_ic_filter_most_near));
        lvElement.add(new ImgTextViewModel("Xem nhiều",R.drawable.home_ic_filter_latest));
        lvElement.add(new ImgTextViewModel("Du Khách",R.drawable.home_ic_filter_tourist));
    }
}