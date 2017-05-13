package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DBHandler;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DatabaseController;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.EatingPlaceInfo;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;
//Màn hình chính ban đầu
public class DefaultScreen extends Fragment {
    GridView grv;
    View rootView;
    ArrayList<ImgTextViewModel> gridElement;
    List<EatingPlaceInfo> ePInf;
    ListView lv;
    private DatabaseController databaseController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.default_screen,container,false);
        LoadData();
        lv = (ListView) rootView.findViewById(R.id.foodyList);
        lv.setAdapter(new customEatingPlaceListAdapter(this.getContext(),ePInf));

        return rootView;
    }
    //Lấy dữ liệu để hiển thị những quán ăn
    private void LoadData(){
        databaseController = new DatabaseController(getContext());
        File database = getContext().getDatabasePath(DatabaseController.DBNAME);
        //Nếu chưa có database
        if(false == database.exists()) {
            databaseController.getReadableDatabase();
            DBHandler dbHandler = new DBHandler(getContext());
            //Copy db
            if(dbHandler.copyDatabase(getContext()) == false) {
                return;
            }
        }
        //lay list
        ePInf = new ArrayList<>();
        ePInf = databaseController.GetEatingPlacesByCity();
    }
}