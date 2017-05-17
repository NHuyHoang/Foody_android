package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.app.Application;

import hhoang.a14110066_nguyenhuyhoang_foody.Model.EatingPlaceInfo;

//1 class để thực hiện lưu các biến global
//các dữ liệu về thành phố đường quận được lấy từ biến trong class này
//các class sẽ set các biến này để lấy dữ liệu thích hợp
public class MyApplication extends Application {

    private String someVariable;
    private String city;
    private String district;
    private String roads;

    private int cityid = -1;
    private int districtid = -1;
    private int roadid = -1;

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public int getRoadid() {
        return roadid;
    }

    public void setRoadid(int roadid) {
        this.roadid = roadid;
    }

    private EatingPlaceInfo ePInf = new EatingPlaceInfo();

    public String getRoads() {
        return roads;
    }

    public void setRoads(String roads) {
        this.roads = roads;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        roads="";
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        districtid = -1;
        district ="";
        this.city = city;
    }

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
}