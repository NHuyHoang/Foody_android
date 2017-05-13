package hhoang.a14110066_nguyenhuyhoang_foody.Model;

//model chứa thông tin tỉnh thành quận đường
public class ProvinceCity {
    String id;
    String tp;
    String quan;
    String duong;

    public ProvinceCity() {
    }

    public ProvinceCity(String id, String tp, String quan, String duong) {
        this.id = id;
        this.tp = tp;
        this.quan = quan;
        this.duong = duong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getDuong() {
        return duong;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }
}
