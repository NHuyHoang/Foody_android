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
//tab danh mục
public class Tab2 extends Fragment {
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


    private void initListViewElement() {
        lvElement = new ArrayList<ImgTextViewModel>();
        lvElement.add(new ImgTextViewModel("Sang trọng", R.drawable.danhmuc_sangtrong));
        lvElement.add(new ImgTextViewModel("Buffet", R.drawable.danhmuc_buffet));
        lvElement.add(new ImgTextViewModel("Nhà hàng", R.drawable.danhmuc_nhahang));
        lvElement.add(new ImgTextViewModel("Ăn vặt/vỉa hè", R.drawable.danhmuc_anvat));
        lvElement.add(new ImgTextViewModel("Ăn chay", R.drawable.danhmuc_anchay));
        lvElement.add(new ImgTextViewModel("Cafe/Dessert", R.drawable.danhmuc_cafe));
        lvElement.add(new ImgTextViewModel("Quán ăn", R.drawable.danhmuc_quanan));
        lvElement.add(new ImgTextViewModel("Bar/Pub", R.drawable.danhmuc_bar));
        lvElement.add(new ImgTextViewModel("Quán nhậu", R.drawable.danhmuc_quannhau));
        lvElement.add(new ImgTextViewModel("Beer club", R.drawable.danhmuc_beerclub));
        lvElement.add(new ImgTextViewModel("Tiệm bánh", R.drawable.danhmu_tiembanh));
        lvElement.add(new ImgTextViewModel("Tiệc tận nơi", R.drawable.danhmuc_tiectannoi));
        lvElement.add(new ImgTextViewModel("Shop Online", R.drawable.danhmuc_shoponline));
        lvElement.add(new ImgTextViewModel("Giao cơm văn phòng", R.drawable.danhmuc_giaocom));
        lvElement.add(new ImgTextViewModel("Khu ẩm thực", R.drawable.danhmuc_khuamthuc));
        lvElement.add(new ImgTextViewModel("Việt Nam", R.drawable.danhmuc_vietnam));
        lvElement.add(new ImgTextViewModel("Châu Mỹ", R.drawable.danhmuc_chaumy));
        lvElement.add(new ImgTextViewModel("Mỹ", R.drawable.danhmuc_my));
        lvElement.add(new ImgTextViewModel("Tây Âu", R.drawable.danhmuc_my));
        lvElement.add(new ImgTextViewModel("Ý", R.drawable.danhmuc_y));
        lvElement.add(new ImgTextViewModel("Pháp", R.drawable.danhmuc_phap));
        lvElement.add(new ImgTextViewModel("Đức", R.drawable.danhmuc_duc));
        lvElement.add(new ImgTextViewModel("Tây Ban Nha", R.drawable.danhmuc_taybannha));
        lvElement.add(new ImgTextViewModel("Trung Hoa", R.drawable.danhmu_tiembanh));
        lvElement.add(new ImgTextViewModel("Ấn Độ", R.drawable.danhmu_tiembanh));
        lvElement.add(new ImgTextViewModel("Thái Lan", R.drawable.danhmuc_thai));
        lvElement.add(new ImgTextViewModel("Nhật Bản", R.drawable.danhmuc_nhatban));
        lvElement.add(new ImgTextViewModel("Hàn Quốc", R.drawable.danhmuc_hanquoc));
        lvElement.add(new ImgTextViewModel("Malaysia", R.drawable.danhmuc_malaysia));
        lvElement.add(new ImgTextViewModel("Quốc tế", R.drawable.danhmuc_quocte));

    }
}