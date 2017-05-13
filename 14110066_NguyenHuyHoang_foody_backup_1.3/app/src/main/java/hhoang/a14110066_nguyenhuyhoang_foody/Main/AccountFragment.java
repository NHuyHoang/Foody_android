package hhoang.a14110066_nguyenhuyhoang_foody.Main;

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
//Fragment tài khoản
public class AccountFragment extends Fragment {
    ListView lv;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.account_fragment,container,false);

        lv = (ListView) rootView.findViewById(R.id.custom_list1);
        lv.setAdapter(new Frag1ListViewCustomAdapter(getContext(),initListViewElement1()));

        lv = (ListView) rootView.findViewById(R.id.custom_list2);
        lv.setAdapter(new Frag1ListViewCustomAdapter(getContext(),initListViewElement2()));

        lv = (ListView) rootView.findViewById(R.id.custom_list3);
        lv.setAdapter(new Frag1ListViewCustomAdapter(getContext(),initListViewElement3()));

        return rootView;
    }
    //Khởi tạo các dòng gồm image và text
    private ArrayList<ImgTextViewModel> initListViewElement1(){
        ArrayList<ImgTextViewModel> lvElement = new ArrayList<ImgTextViewModel>();
        lvElement.add(new ImgTextViewModel("Lịch sử đặt chỗ",R.drawable.ic_sttnotification_tablenow));
        lvElement.add(new ImgTextViewModel("Lịch sử giao hàng",R.drawable.ic_sttnotification_deli));
        lvElement.add(new ImgTextViewModel("Coupon",R.drawable.ic_ecoupon));
        lvElement.add(new ImgTextViewModel("Sử dụng Ecard",R.drawable.ic_ecard));
        lvElement.add(new ImgTextViewModel("Lịch sử Eat-in/Take away",R.drawable.ic_pos));
        return lvElement;
    }

    private ArrayList<ImgTextViewModel> initListViewElement2(){
        ArrayList<ImgTextViewModel> lvElement = new ArrayList<ImgTextViewModel>();
        lvElement.add(new ImgTextViewModel("Thông tin & liên hệ",R.drawable.ic_profile_contact));
        lvElement.add(new ImgTextViewModel("Tiền thưởng",R.drawable.ic_e_coupon_buy_credits_black));
        lvElement.add(new ImgTextViewModel("Thanh toán",R.drawable.ic_more_payment));
        lvElement.add(new ImgTextViewModel("Thiết lập tài khoản",R.drawable.ic_more_setting));
        return lvElement;
    }

    private ArrayList<ImgTextViewModel> initListViewElement3(){
        ArrayList<ImgTextViewModel> lvElement = new ArrayList<ImgTextViewModel>();
        lvElement.add(new ImgTextViewModel("Mời bạn bè",R.drawable.ic_invite_friend));
        lvElement.add(new ImgTextViewModel("Góp ý",R.drawable.ic_email));
        lvElement.add(new ImgTextViewModel("Cài đặt ứng dụng",R.drawable.ic_more_setting));
        return lvElement;
    }

}