package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.MyApplication;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.RestaurantInf;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ReviewModel;

//Class thực hiện lấy dữ liệu từ Restfull webservice
//
public class RestClientController implements RestClientEvent  {
    List<Header> headers = new ArrayList<Header>();
    JSONObject object = new JSONObject();
    Context context;
    List<ReviewModel> reviewList = new ArrayList<ReviewModel>();
    List<RestaurantInf> restaurantList = new ArrayList<RestaurantInf>();
    RestClientEvent callback;

    public RestClientController(Context context,RestClientEvent callback){
        this.context = context;
        this.callback = callback;
    }

    public List<ReviewModel> getReviewList() {
        return reviewList;
    }

    public List<RestaurantInf> getRestaurantList() {
        return restaurantList;
    }

    //Lay id tp tren server
    public void GetCityId(){

        String cityName = ((MyApplication)context.getApplicationContext()).getCity();
        ((MyApplication)context.getApplicationContext()).setDistrictid(-1);
        String url = "getcityid/"+cityName;
        new ASyncTask(this,context,url,"GetCityId").execute();

    }
    private void SetCityId(JSONArray jsonArr) {
        int id = -1;
        try {
            JSONObject json = jsonArr.getJSONObject(0);
            id = json.getInt("cityid");
            ((MyApplication)context.getApplicationContext()).setCityid(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Lay id district tren server
    public void GetDisId(){

        String cityName = ((MyApplication)context.getApplicationContext()).getCity();
        String disName = ((MyApplication)context.getApplicationContext()).getDistrict();
        String url = "getdisid/"+cityName+"/"+disName;
        new ASyncTask(this,context,url,"GetDisId").execute();

    }
    public void SetDisId(JSONArray jsonArr) {
        int id = -1;
        try {
            JSONObject json = jsonArr.getJSONObject(0);
            id = json.getInt("disid");
            ((MyApplication)context.getApplicationContext()).setDistrictid(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Lay thong tin cac quan an
    public void GetResList(){
        int cityid = ((MyApplication)context.getApplicationContext()).getCityid();
        int  disid = ((MyApplication)context.getApplicationContext()).getDistrictid();
        String url = "";
        if(disid == -1 ) url = "getresbycity/"+cityid;
        else url = "getresbydis/"+disid;
        new ASyncTask(callback,context,url,"GetResList").execute();
    }
    //Set list cac quan an
    public List<RestaurantInf> SetResList(JSONArray jsonArr){
        List<RestaurantInf> resList = new ArrayList<RestaurantInf>();
        try {
            for (int i = 0; i < jsonArr.length(); i++){
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                resList.add(new RestaurantInf(jsonObj,reviewList));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resList;
    }
    //Lay review
    public  void GetReview(){
        int cityid = ((MyApplication)context.getApplicationContext()).getCityid();
        int  disid = ((MyApplication)context.getApplicationContext()).getDistrictid();
        String url = "";
        if(disid == -1 ) url = "getreviewbycity/"+cityid;
        else url = "getreviewbydis/"+disid;
        new ASyncTask(callback,context,url,"GetReview").execute();
    }
    //set review
    public List<ReviewModel> SetReview(JSONArray jsonArr){
        List<ReviewModel> revList = new ArrayList<ReviewModel>();
        try {
            for (int i = 0; i < jsonArr.length(); i++){
                revList.add(new ReviewModel(jsonArr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return revList;
    }

    @Override
    public void onEventCompleted(JSONArray json,String methodcall) {
        switch (methodcall){
            case("GetDisId"):SetDisId(json);break;
            case("GetCityId"):SetCityId(json);break;
            case("getResList"):restaurantList = SetResList(json);break;
            case("GetReview"):reviewList = SetReview(json);break;
        }
    }

    @Override
    public void onEventFailed() {

    }
}
