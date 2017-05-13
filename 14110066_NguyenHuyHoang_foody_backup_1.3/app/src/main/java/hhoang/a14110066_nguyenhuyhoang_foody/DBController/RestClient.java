package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SONY on 5/11/2017.
 */

public class RestClient {
    private static final String BASE_URL = "http://192.168.1.207:5000/api/res/";

    private static SyncHttpClient client = new SyncHttpClient();


    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        String AbsoluteUrl = getAbsoluteUrl(url);
        client.get(context, AbsoluteUrl, headers, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
