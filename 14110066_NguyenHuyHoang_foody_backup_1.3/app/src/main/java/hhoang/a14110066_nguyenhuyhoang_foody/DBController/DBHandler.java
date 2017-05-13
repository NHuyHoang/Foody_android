package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by SONY on 4/8/2017.
 */

//Hàm ghi dữ liệu vào trong device
public class DBHandler {
    Context context;
    public DBHandler(Context context){
        this.context = context;
    }
    public boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseController.DBNAME);
            String outFileName = DatabaseController.DBLOCATION + DatabaseController.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
