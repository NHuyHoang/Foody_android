package hhoang.a14110066_nguyenhuyhoang_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ProvinceCity;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//list view cho activity chọn tp
public class ListAdapter extends BaseAdapter {
    List<String> lvMod;
    Context context;
    private static LayoutInflater inflater = null;
    public ListAdapter(Context context, List<String> lvMod){
        this.lvMod = lvMod;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return lvMod.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.default_listrow,parent,false);
        TextView tv = (TextView) rowView.findViewById(R.id.listText);
        tv.setText(lvMod.get(position));
        return rowView;
    }
}
