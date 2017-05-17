package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import hhoang.a14110066_nguyenhuyhoang_foody.Adapter.DisplayRestaurantAdapter;
import hhoang.a14110066_nguyenhuyhoang_foody.Adapter.customEatingPlaceListAdapter;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DBHandler;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DatabaseController;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.RestClient;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.RestClientController;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.RestClientEvent;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.EatingPlaceInfo;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.RestaurantInf;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ReviewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;
//Màn hình chính ban đầu
public class DefaultScreen extends Fragment implements RestClientEvent {
    GridView grv;
    View rootView;
    ArrayList<ImgTextViewModel> gridElement;
    List<EatingPlaceInfo> ePInf;
    List<ReviewModel> reviewModels = new ArrayList<>();
    ListView lv;
    private DatabaseController databaseController;
    JSONArray json = new JSONArray();
    RestClientController restClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.default_screen,container,false);
        restClient = new RestClientController(getContext(),this);
        LoadData();

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
        restClient.GetReview();
    }

    public void SetResList(JSONArray jsonArr){
        List<RestaurantInf> resList = new ArrayList<RestaurantInf>();
        resList = restClient.SetResList(jsonArr);
        List<ReviewModel> reviewModels = new ArrayList<>();
        reviewModels = this.reviewModels;
        //List<Integer> resId = new ArrayList<Integer>();
        HashMap<Integer,List<ReviewModel>> mapReview = new HashMap<>();
        for (ReviewModel revModel: reviewModels) {
            int resid = revModel.getItemid();
            if (mapReview.get(resid) != null) continue;
            List<ReviewModel> temp = new ArrayList<>();
            //int pos = 0;
            for (ReviewModel revModel1: reviewModels) {
                if(revModel1.getItemid() == resid) temp.add(revModel1);
            }
            mapReview.put(resid,temp);
        }
        for(RestaurantInf resInf:resList){
            int resid = resInf.getId();
            resInf.setRevList(mapReview.get(resid));
        }
        DisplayRestaurantAdapter adapter = new DisplayRestaurantAdapter(resList,getContext());
        lv = (ListView) rootView.findViewById(R.id.foodyList);
        lv.setAdapter(adapter);
    }

    public void SetReview(JSONArray jsonArr){
        reviewModels = restClient.SetReview(jsonArr);
        restClient.GetResList();
    }

    @Override
    public void onEventCompleted(JSONArray objects, String methodcall) {
        switch (methodcall){
            case("GetResList"):SetResList(objects);break;
            case("GetReview"):SetReview(objects);break;
        }
    }

    @Override
    public void onEventFailed() {

    }
}