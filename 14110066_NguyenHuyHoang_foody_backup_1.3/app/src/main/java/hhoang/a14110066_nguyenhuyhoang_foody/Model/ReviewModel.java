package hhoang.a14110066_nguyenhuyhoang_foody.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by SONY on 5/13/2017.
 */

public class ReviewModel {
    int id;
    String name;
    double rating;
    String comment;
    String commenttrim;
    String avatar;
    int itemid;
    String reviewurl;
    String imgsrc;
    public ReviewModel() {
    }

    public ReviewModel(JSONObject jsonObj) {
        try {
            imgsrc = jsonObj.getString("imgsrc");
            JSONObject json = jsonObj.getJSONObject("review");
            id = json.getInt("id");
            name = json.getString("name");
            rating = json.getDouble("rating");
            comment = json.getString("comment");
            commenttrim = json.getString("commenttrim");
            avatar = json.getString("avatar");
            itemid = json.getInt("itemid");
            reviewurl = json.getString("reviewurl");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ReviewModel(int id, String name, double rating, String comment, String commenttrim, String avatar, int itemid, String reviewurl,String imgsrc) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
        this.commenttrim = commenttrim;
        this.avatar = avatar;
        this.itemid = itemid;
        this.reviewurl = reviewurl;
        this.imgsrc = imgsrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenttrim() {
        return commenttrim;
    }

    public void setCommenttrim(String commenttrim) {
        this.commenttrim = commenttrim;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getReviewurl() {
        return reviewurl;
    }

    public void setReviewurl(String reviewurl) {
        this.reviewurl = reviewurl;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

}