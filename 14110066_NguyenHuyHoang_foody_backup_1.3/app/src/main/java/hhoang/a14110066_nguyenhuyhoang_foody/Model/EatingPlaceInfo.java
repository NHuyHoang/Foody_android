package hhoang.a14110066_nguyenhuyhoang_foody.Model;

//Model chứa thông tin địa điểm ăn uống

public class EatingPlaceInfo {
    public String id;
    public String name;
    public String locale;
    public int img;
    public float point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EatingPlaceInfo() {
        this.id="";
        this.name="";
        this.locale="";
        this.img=0;
        this.point=0;
    }

    public EatingPlaceInfo(String id,String name, String locale, int img, float point) {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.img = img;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
}
