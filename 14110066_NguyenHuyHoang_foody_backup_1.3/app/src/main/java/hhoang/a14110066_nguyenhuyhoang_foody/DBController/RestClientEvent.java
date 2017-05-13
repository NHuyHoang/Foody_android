package hhoang.a14110066_nguyenhuyhoang_foody.DBController;

import org.json.JSONArray;

import java.util.Objects;

/**
 * Created by SONY on 5/11/2017.
 */

public interface RestClientEvent {
    public void onEventCompleted(JSONArray objects,String methodcall);
    public void onEventFailed();
}
