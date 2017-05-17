package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import android.content.Context;
import android.os.AsyncTask;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

/**
 * Created by SONY on 5/11/2017.
 */
//Class thực hiện các thread 1cach đồng bộ
//Khi có 1 class khác gọi hàm .execute class sẽ thực hiện phương thức doInBackground() trước khi hoàn thành thì thực hiện  onPostExecute()
//
public class ASyncTask extends AsyncTask<Void, Void, Void> {

    private RestClientEvent callback;
    private String url;
    private Context context;
    private String methodcall;
    JSONArray json = new JSONArray();


    //ASyncTask(class callback, context, url, phương thức đã gọi class)
    public ASyncTask(RestClientEvent cb, Context context, String url, String methodcall) {
        //Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        callback = cb;
        this.context = context;
        this.url = url;
        this.methodcall = methodcall;
    }
    @Override
    protected Void doInBackground(Void... params) {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RestClient.get(context, url, headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                json = response;
                    }
                });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(callback != null) {
            callback.onEventCompleted(json,methodcall);
        }
    }
}
