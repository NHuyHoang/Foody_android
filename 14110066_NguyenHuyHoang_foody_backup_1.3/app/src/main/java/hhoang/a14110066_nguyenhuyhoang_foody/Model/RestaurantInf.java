package hhoang.a14110066_nguyenhuyhoang_foody.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by SONY on 5/13/2017.
 */

public class RestaurantInf {
    int id;
    String address;
    String resName;
    double avgRate;
    int cateid;
    int typeid;
    String imgid;
    String imgSrc;
    List<ReviewModel> revList;
    int totalimg;
    int totalrev;

    public List<ReviewModel> getRevList() {
        return revList;
    }

    public void setRevList(List<ReviewModel> revList) {
        this.revList = revList;
    }

    public RestaurantInf() {
    }

    public RestaurantInf(int id, String address, String resName, double avgRate, int cateid, int typeid, String imgid, String imgSrc, List<ReviewModel> revList, int totalimg, int totalrev) {
        this.id = id;
        this.address = address;
        this.resName = resName;
        this.avgRate = avgRate;
        this.cateid = cateid;
        this.typeid = typeid;
        this.imgid = imgid;
        this.imgSrc = imgSrc;
        this.revList = revList;
        this.totalimg = totalimg;
        this.totalrev = totalrev;

    }

    public RestaurantInf(JSONObject jsonObj,List<ReviewModel> revList) {
        try {
            id = jsonObj.getInt("resid");
            address = jsonObj.getString("address");
            resName = jsonObj.getString("resname");
            avgRate = jsonObj.getDouble("avgrate");
            cateid = jsonObj.getInt("cateid");
            typeid = jsonObj.getInt("typeid");
            totalimg = jsonObj.getInt("totalimg");
            totalrev = jsonObj.getInt("totalrev");
            imgid = jsonObj.getString("imgid");
            imgSrc = jsonObj.getString("imgsrc");
            this.revList = revList;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getTotalimg() {
        return totalimg;
    }

    public void setTotalimg(int totalimg) {
        this.totalimg = totalimg;
    }

    public int getTotalrev() {
        return totalrev;
    }

    public void setTotalrev(int totalrev) {
        this.totalrev = totalrev;
    }
}
