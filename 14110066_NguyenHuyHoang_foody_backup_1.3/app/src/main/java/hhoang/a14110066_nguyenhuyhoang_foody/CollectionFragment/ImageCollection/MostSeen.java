package hhoang.a14110066_nguyenhuyhoang_foody.CollectionFragment.ImageCollection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hhoang.a14110066_nguyenhuyhoang_foody.R;


public class MostSeen extends Fragment{
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.error,container,false);
        return rootView;
    }
}
