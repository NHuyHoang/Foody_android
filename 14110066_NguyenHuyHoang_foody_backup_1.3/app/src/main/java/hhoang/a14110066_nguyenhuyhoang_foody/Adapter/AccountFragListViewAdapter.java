package hhoang.a14110066_nguyenhuyhoang_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Custom adapter cho các listview có dạng gồm một Imageview và TextView trên 1 dòng
public class AccountFragListViewAdapter extends BaseAdapter {
    ArrayList<ImgTextViewModel> lvMod;
    Context context;
    private static LayoutInflater inflater = null;
    public AccountFragListViewAdapter(Context context, ArrayList<ImgTextViewModel> lvMod){
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

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.account_listview_row,parent,false);
        holder.tv = (TextView) rowView.findViewById(R.id.listText);
        holder.img = (ImageView) rowView.findViewById(R.id.listImg);
        holder.tv.setText(lvMod.get(position).tv);
        holder.img.setImageResource(lvMod.get(position).img);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked ", Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}