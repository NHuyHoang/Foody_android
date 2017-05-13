package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.MyApplication;

//Class thực hiện lấy dữ liệu từ Restfull webservice
//
public class RestClientController implements RestClientEvent  {
    List<Header> headers = new ArrayList<Header>();
    JSONObject object = new JSONObject();
    Context context;

    public RestClientController(Context context){
        this.context = context;
    }

    //Lay id tp tren server
    public void GetCityId(){

        String cityName = ((MyApplication)context.getApplicationContext()).getCity();
        String url = "getcityid/"+cityName;
        new SyncTask(this,context,url,"GetCityId").execute();

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
        new SyncTask(this,context,url,"GetDisId").execute();

    }
    private void SetDisId(JSONArray jsonArr) {
        int id = -1;
        try {
            JSONObject json = jsonArr.getJSONObject(0);
            id = json.getInt("disid");
            ((MyApplication)context.getApplicationContext()).setDistrictid(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onEventCompleted(JSONArray json,String methodcall) {
        switch (methodcall){
            case("GetDisId"):SetDisId(json);
            case("GetCityId"):SetCityId(json);
        }
    }



    @Override
    public void onEventFailed() {

    }
}
