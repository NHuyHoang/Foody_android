package hhoang.a14110066_nguyenhuyhoang_foody.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hhoang.a14110066_nguyenhuyhoang_foody.Main.DefaultScreenCustomGrid;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.RestaurantInf;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

import static android.R.attr.bitmap;

/**
 * Created by SONY on 5/13/2017.
 */

public class DisplayRestaurantAdapter extends BaseAdapter {
    List<RestaurantInf> restaurantList;
    Context context;
    private static LayoutInflater inflater = null;
    ArrayList<ImgTextViewModel> gridElement;
    public DisplayRestaurantAdapter(List<RestaurantInf> restaurantList,Context context){
        this.restaurantList = restaurantList;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return restaurantList.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView name;
        TextView locale;
        ImageView img;
        TextView point;
        TextView totalimg;
        TextView totalrev;
        ImageView ava1;
        ImageView ava2;
        TextView username1;
        TextView username2;
        TextView comment1;
        TextView comment2;
        TextView rate1;
        TextView rate2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        position--;
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.eating_place_customlist,parent,false);
        //ban đầu sẽ gắn gridview vào trc
        if ( position == -1){
            View gridview = inflater.inflate(R.layout.defaultscreen_initial_list,parent,false);
            initGridViewElement();
            GridView grv = (GridView)gridview.findViewById(R.id.grid);
            //set custom grid view adapter cho gridview
            grv.setAdapter(new DefaultScreenCustomGrid(context,gridElement));
            return gridview;
        }

        holder.name = (TextView) rowView.findViewById(R.id.name);
        holder.locale = (TextView) rowView.findViewById(R.id.locale);
        holder.img = (ImageView) rowView.findViewById(R.id.image);
        holder.point = (TextView) rowView.findViewById(R.id.point);
        holder.totalimg = (TextView) rowView.findViewById(R.id.totalimg);
        holder.totalrev = (TextView) rowView.findViewById(R.id.totalrev);

        holder.ava1 = (ImageView) rowView.findViewById(R.id.avatar1);
        holder.ava2 = (ImageView) rowView.findViewById(R.id.avatar2);
        holder.username1 = (TextView) rowView.findViewById(R.id.username1);
        holder.username2 = (TextView) rowView.findViewById(R.id.username2);
        holder.comment1 = (TextView) rowView.findViewById(R.id.comment1);
        holder.comment2 = (TextView) rowView.findViewById(R.id.comment2);
        holder.rate1 = (TextView) rowView.findViewById(R.id.rating1);
        holder.rate2 = (TextView) rowView.findViewById(R.id.rating2);
        String locale = restaurantList.get(position).getAddress();

        if (locale.length()>=80){
            locale = locale.substring(0,70);
            locale+="...";
        }

        String name = restaurantList.get(position).getResName();
        if (name.length()>=40){
            name = name.substring(0,30);
            name+=".....";
        }

        holder.name.setText(name);
        holder.locale.setText(locale);
        int totalrev = restaurantList.get(position).getTotalrev();
        int totalimg = restaurantList.get(position).getTotalimg();
        holder.totalrev.setText(String.valueOf(totalrev));
        holder.totalimg.setText(String.valueOf(totalimg));
        Bitmap b = bitmapdecode(restaurantList.get(position).getImgSrc());
        holder.img.setImageBitmap(b);
        double avgrate = restaurantList.get(position).getAvgRate();
        avgrate = Math.round(avgrate*10);
        avgrate = avgrate/10;
        holder.point.setText(String.valueOf(avgrate));

        String ava1 = restaurantList.get(position).getRevList().get(0).getImgsrc();
        String ava2 = restaurantList.get(position).getRevList().get(1).getImgsrc();
        if(ava1 != null) {
            b = bitmapdecode(ava1);
            Drawable d = new BitmapDrawable(context.getResources(),b);
            holder.ava1.setBackgroundDrawable(d);
        }
        if(ava2 != null) {
            b = bitmapdecode(ava2);
            Drawable d = new BitmapDrawable(context.getResources(),b);
            holder.ava2.setBackgroundDrawable(d);
        }

        String username1 = restaurantList.get(position).getRevList().get(0).getName();
        String username2 = restaurantList.get(position).getRevList().get(1).getName();
        holder.username1.setText(username1);
        holder.username2.setText(username2);

        String cmt1 = restaurantList.get(position).getRevList().get(0).getCommenttrim();
        String cmt2 = restaurantList.get(position).getRevList().get(1).getCommenttrim();
        holder.comment1.setText(cmt1);
        holder.comment2.setText(cmt2);

        double rate1 =  restaurantList.get(position).getRevList().get(0).getRating();
        double rate2 =  restaurantList.get(position).getRevList().get(1).getRating();
        holder.rate1.setText(String.valueOf(rate1));
        holder.rate2.setText(String.valueOf(rate2));

        return rowView;
    }

    private Bitmap bitmapdecode(String imgStr){
        byte[] b = Base64.decode(imgStr.getBytes(), 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[1024 *32];
        Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length, options);
        return bm;
    }

    private void initGridViewElement(){
        gridElement = new ArrayList<ImgTextViewModel>();
        gridElement.add(new ImgTextViewModel("Gần tôi", R.drawable.ic_nearby));
        gridElement.add(new ImgTextViewModel("Coupon",R.drawable.ic_ecoupon));
        gridElement.add(new ImgTextViewModel("Đặt chỗ ưu đãi",R.drawable.ic_sttnotification_tablenow));
        gridElement.add(new ImgTextViewModel("Đặt giao hàng",R.drawable.ic_sttnotification_deli));
        gridElement.add(new ImgTextViewModel("E-card",R.drawable.ic_ecard));
        gridElement.add(new ImgTextViewModel("Game & Fun",R.drawable.ic_game));
        gridElement.add(new ImgTextViewModel("Bình luận",R.drawable.ic_icon_binhluanmoi));
        gridElement.add(new ImgTextViewModel("Blogs",R.drawable.ic_reporttraffic));
        gridElement.add(new ImgTextViewModel("Top thành viên",R.drawable.ic_icon_topthanhvien));
        gridElement.add(new ImgTextViewModel("Video",R.drawable.ic_video));
    }
}
