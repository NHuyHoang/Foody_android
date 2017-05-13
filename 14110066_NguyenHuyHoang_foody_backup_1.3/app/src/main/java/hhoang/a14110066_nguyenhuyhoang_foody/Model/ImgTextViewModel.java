package hhoang.a14110066_nguyenhuyhoang_foody.Model;

import android.widget.ImageView;
import android.widget.TextView;

//model chứa các hình ảnh và text
//thường được các listview sử dụng
public class ImgTextViewModel {
    public String tv;
    public int img;

    public ImgTextViewModel(String tv, int img) {
        this.tv = tv;
        this.img = img;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
