package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.EatingPlaceInfo;
import hhoang.a14110066_nguyenhuyhoang_foody.Main.MyApplication;

/**
 * Created by SONY on 4/8/2017.
 */

public class DatabaseController extends SQLiteOpenHelper {
    public static final String DBNAME = "FoodyDb.sqlite";
    public static String DBLOCATION = "/data/data/com.gnirt69.sqlitefromassetexample/databases/";
    public static final String TINHTHANHTBL="TINHTHANH";
    public static final String  QUANHUYEN="QUAN";
    public static final String  DUONG="DUONG";
    private Context mContext;
    private SQLiteDatabase mDatabase;



    public DatabaseController(Context context){
        super(context, DBNAME, null, 1);
        this.mContext = context;
        DBLOCATION = mContext.getApplicationInfo().dataDir + "/databases/";


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //Mở csdl
    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void closeDatabase(){
        if(mDatabase!=null){
            mDatabase.close();
        }
    }





    //Lay ten tp
    public List<String> getProvinceCityList(){
        List<String> pcl = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT DISTINCT TP FROM "+TINHTHANHTBL,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            pcl.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return pcl;
    }
    //Lay Ds cac quan theo ten tp
    public List<String> getCityDistrictList(String cityName){
        List<String> district = new ArrayList<>();
        openDatabase();;
        Cursor cursor = mDatabase.rawQuery("SELECT DISTINCT "+QUANHUYEN+" FROM "+TINHTHANHTBL+" WHERE TP = '"+cityName+"' ORDER BY "+QUANHUYEN+" ASC ",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            district.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return district;
    }

    //Lay ten duong theo quan huyen
    public List<String> getRoadsList(String cityName,String districtName){
        List<String> childList = new ArrayList<>();
        openDatabase();;
        Cursor cursor = mDatabase.rawQuery("SELECT DISTINCT "+DUONG+
                                            " FROM "+TINHTHANHTBL+
                                            " WHERE TP = '"+cityName+"' AND " +
                "                           QUAN = '"+districtName+"'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            childList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return childList;
    }
    //Tra ve Hashmap quan va ten duong theo tp
    //vd: Quan1:_Hai Ba Trung
    //          _Vo Thi Sau
    //    Quan10:_CMT 8
    //           _3 Tháng 2
    public HashMap<String,List<String>> getAllDistrictRoadsList(String cityName){
        HashMap<String,List<String>> result = new HashMap<String,List<String>>();
        List<String> districtList = getCityDistrictList(cityName);
        for(String districtName : districtList){
            List<String> roadsList = getRoadsList(cityName,districtName);
            result.put(districtName,roadsList);
        }
        return result;
    }
    //Trả về Hashmap tp quận và tên đường
    //vd: Tp.Hcm: Quận 1:_Hai Bà Trưngg
    //                   _Võ Thị Sáu
    //    Khánh Hòa:Nha Trang:_Thống Nhất
    //                        _Trần Phú
    public HashMap<String,HashMap<String,List<String>>> getAllCityDistrictRoads(String cityName){
        HashMap<String,HashMap<String,List<String>>> result = new HashMap<>();
        List<String> cityList  = getProvinceCityList();
        for(String cityNam:cityList){
            HashMap<String,List<String>> districtRoads = getAllDistrictRoadsList(cityName);
            result.put(cityName,districtRoads);
        }
        return result;
    }
    //Hàm lấy list thông tin các quán ăn dựa trên các biến global
    public List<EatingPlaceInfo>GetEatingPlacesByCity(){
        List<EatingPlaceInfo> ePInf = new ArrayList<>();

        openDatabase();
        String cityName = ((MyApplication)mContext.getApplicationContext()).getCity();
        String district = ((MyApplication)mContext.getApplicationContext()).getDistrict();
        String roads = ((MyApplication)mContext.getApplicationContext()).getRoads();
        String getcursor;
        //với các giá trị tp quận đường sẽ sử dụng các câu lệnh sql tương ứng
        if(cityName != null && cityName != "" ){
            getcursor = "select quan.idquan,ten,vtchitiet,idanh,diem " +
                        "from quan,vitri,anh " +
                        "where quan.idquan = vitri.idquan and " +
                        "quan.idquan = anh.idquan and " +
                        "vitri.idvt in (select id from tinhthanh where tp ='"+cityName+"') ";
            if(district != null && district != "")
                getcursor = "select quan.idquan,ten,vtchitiet,idanh,diem " +
                            "from quan,vitri,anh " +
                            "where quan.idquan = vitri.idquan and " +
                            "quan.idquan = anh.idquan and " +
                            "vitri.idvt in (select id from tinhthanh " +
                                            "where tp = '"+cityName+"' and " +
                                            "quan ='"+district+"')";
            if(roads != null && roads != "")
                getcursor = "select quan.idquan,ten,vtchitiet,idanh,diem " +
                            "from quan,vitri,anh " +
                            "where quan.idquan = vitri.idquan and " +
                            "quan.idquan = anh.idquan and " +
                            "vitri.idvt in (select id from tinhthanh " +
                                            "where tp = '"+cityName+"' and " +
                                            "quan = '"+district+"' and "+
                                            "duong ='"+roads+"')";
        }
        //Không có dl thì thoát
        else{ return ePInf;}
        Cursor cursor =
                mDatabase.rawQuery(getcursor,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int resourceID =
                    mContext.getResources().getIdentifier(cursor.getString(3), "drawable",mContext.getPackageName());
            EatingPlaceInfo eatingPlaceInfo =
                    new EatingPlaceInfo(cursor.getString(0),cursor.getString(1),cursor.getString(2),resourceID,cursor.getFloat(4));
            ePInf.add(eatingPlaceInfo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return ePInf;
    }
}
