package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Custom grid addapter gồm icon hình ảnh và text
public class DefaultScreenCustomGrid extends BaseAdapter {
    private Context context;
    private ArrayList<ImgTextViewModel> arr;

    public DefaultScreenCustomGrid(Context context,ArrayList<ImgTextViewModel> arr){
        this.context = context;
        this.arr = arr;
    }
    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        grid = inflater.inflate(R.layout.default_screen_gridview,parent,false);
        holder.tv = (TextView) grid.findViewById(R.id.grid_text);
        holder.img = (ImageView) grid.findViewById(R.id.grid_image);
        holder.tv.setText(arr.get(position).tv);
        holder.img.setImageResource(arr.get(position).img);

        return grid;
    }
}
