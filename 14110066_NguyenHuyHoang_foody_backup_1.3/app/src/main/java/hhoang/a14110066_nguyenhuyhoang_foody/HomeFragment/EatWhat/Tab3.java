package hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DBHandler;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DatabaseController;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.RestClientController;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.ExpandableListAdapter;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.MyApplication;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.ProvinceCityChoice;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

import static hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhat.FragEatWhat.mTabHost;

//Tab để chọn thay đổi thành phố quận huyện đường
public class Tab3 extends Fragment {
    private ExpandableListView expLv;
    private Map<String, List<String>> mData;
    private DatabaseController databaseController;
    private ExpandableListAdapter adapter;

    TabHost tabHost;
    View rootView;
    @Nullable

    @Override
    public void onResume()
    {
        //Khi quay lại tab
        super.onResume();
        String s = ((MyApplication)getActivity().getApplication()).getCity();
        TextView tv = (TextView) rootView.findViewById(R.id.cityName);
        tv.setText(s);
        LoadData();
        TextView textView = (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        textView.setText(s);
        //Chỉnh lại tên tabhost thành tên tp
        textView = (TextView) hhoang.a14110066_nguyenhuyhoang_foody.HomeFragment.EatWhere.FragEatWhere.mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        textView.setText(s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment3,container,false);
        expLv = (ExpandableListView)rootView.findViewById(R.id.dRoadsList);
        //Khi nhấn vào group tức là chọn quận/huyện
        expLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                TextView tv =(TextView)v.findViewById(R.id.groudHeaderTV);
                //set là biến giá trị quận
                ((MyApplication)getContext().getApplicationContext()).setDistrict(tv.getText().toString());
                //Lay id tren server
                RestClientController resController = new RestClientController(getContext());
                resController.GetDisId();
                //
                int disid = ((MyApplication)getContext().getApplicationContext()).getDistrictid();
                Toast.makeText(getActivity(), String.valueOf(disid),
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
        //khi click vào các con tức là chọn đường
        expLv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                TextView tv =(TextView)v.findViewById(R.id.childRowTv);
                String s = tv.getText().toString();
                //set lại biến global đường
                ((MyApplication)getContext().getApplicationContext()).setRoads(s);
                return true;
            }
        });
        String s = ((MyApplication)getActivity().getApplication()).getCity();
        TextView tv = (TextView) rootView.findViewById(R.id.cityName);
        tv.setText(s);
        databaseController = new DatabaseController(getContext());
        //Chọn thành phố
        Button btn = (Button) rootView.findViewById(R.id.cityBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển đến activity hiển thị ds thành phố
                Intent intent = new Intent(getActivity(), ProvinceCityChoice.class);
                startActivity(intent);
            }
        });

        LoadData();
        return rootView;
    }

    private void LoadData(){
        File database = getContext().getDatabasePath(DatabaseController.DBNAME);
        if(false == database.exists()) {
            databaseController.getReadableDatabase();
            DBHandler dbHandler = new DBHandler(getContext());
            //Copy db
            if(dbHandler.copyDatabase(getContext()) == false) {
                return;
            }
        }
        //lay list
        String s = ((MyApplication)getActivity().getApplication()).getCity();
        mData = new TreeMap<String,List<String>>(databaseController.getAllDistrictRoadsList(s));
        adapter = new ExpandableListAdapter(getContext(),mData);
        expLv.setAdapter(adapter);
    }

}