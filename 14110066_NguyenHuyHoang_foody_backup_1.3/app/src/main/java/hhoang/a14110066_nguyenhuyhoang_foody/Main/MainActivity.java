package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hhoang.a14110066_nguyenhuyhoang_foody.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragment chứa tabhost dưới đáy màn hình
        FooterTabhostActivity fragment = new FooterTabhostActivity();
        //Tp được chọn để hiển thị ban đầu là TP.HCM
        //class MyApplication để chứa các giá trị global
        ((MyApplication)this.getApplication()).setCity("TP.HCM");
        ((MyApplication)this.getApplication()).setCityid(1);
        //Gắn fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, "home_frag").addToBackStack(null).commit();

    }
}
