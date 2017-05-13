package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DBHandler;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.DatabaseController;
import hhoang.a14110066_nguyenhuyhoang_foody.DBController.RestClientController;
import hhoang.a14110066_nguyenhuyhoang_foody.R;


//activity hiển thị để chọn tỉnh/thành
public class ProvinceCityChoice extends Activity {
    private List<String> pcl;
    private ListView lv;
    private DatabaseController databaseController;
    private ListAdapter listAdapter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province_city_choice);

        ImageView imageView = (ImageView)findViewById(R.id.backBtn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProvinceCityChoice.this.finish();
            }
        });

        lv = (ListView)findViewById(R.id.provinceList);
        databaseController = new DatabaseController(this);
        //Lấy csdl
        File database = getApplicationContext().getDatabasePath(DatabaseController.DBNAME);
        //Nếu database chưa tồn tại
        if(false == database.exists()) {
            databaseController.getReadableDatabase();
            DBHandler dbHandler = new DBHandler(this);
            //Copy db
            if(dbHandler.copyDatabase(this) == false) {
                return;
            }
        }
        //lay list các tp

        pcl = databaseController.getProvinceCityList();
        listAdapter = new ListAdapter(this,pcl);
        lv.setAdapter(listAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Khi nhấn vào listrow thì button có tên "mặc định sẽ được hiển thị'
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button btn;
                btn = (Button)view.findViewById(R.id.cityChoice);
                tv = (TextView)view.findViewById(R.id.listText);
                btn.setVisibility(View.VISIBLE);
                //Khi button 'mặc định' click thì sẽ set tên thành phố tương ứng
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MyApplication)ProvinceCityChoice.this.getApplication()).setCity(tv.getText().toString());
                        //
                        RestClientController resController = new RestClientController(getApplicationContext());
                        resController.GetCityId();
                        //
                        ProvinceCityChoice.this.finish();
                    }
                });
            }
        });

    }
}
