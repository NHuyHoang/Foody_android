package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.content.Context;
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
import hhoang.a14110066_nguyenhuyhoang_foody.Model.EatingPlaceInfo;
import hhoang.a14110066_nguyenhuyhoang_foody.Model.ImgTextViewModel;
import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Adapter listview ở màn hình hiển thị chính
//bao gồm 1 dòng chứa hình ảnh
//1 dòng chứa gridview
//dòng còn lại chứa các customlistview hiển thị thông tin quán ăn
public class customEatingPlaceListAdapter extends BaseAdapter {
    //list chứa thông tin các quán ăn
    //bao gồm id,tên,địa điểm,point,id hình ảnh
    List<EatingPlaceInfo> ePInf;
    Context context;
    ArrayList<ImgTextViewModel> gridElement;

    private static LayoutInflater inflater = null;
    public customEatingPlaceListAdapter(Context context,List<EatingPlaceInfo> ePInf){
        this.context = context;
        this.ePInf = ePInf;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //Vì trên đầu màn hình chính có dòng chứa gridview
    //nên số lượng dòng = số lượng phần tử trong list + 1
    @Override
    public int getCount() {
        return ePInf.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return ePInf.get(position);
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
        //các dòng tiếp theo sẽ hiển thị thông tin quán ăn
        holder.name = (TextView) rowView.findViewById(R.id.name);
        holder.locale = (TextView) rowView.findViewById(R.id.locale);
        holder.img = (ImageView) rowView.findViewById(R.id.image);
        holder.point = (TextView) rowView.findViewById(R.id.point);

        //thu gọn chữ nếu quá dài
        String locale = ePInf.get(position).locale;
        if (locale.length()>=80){
            locale = locale.substring(0,70);
            locale+="...";
        }

        String name = ePInf.get(position).name;
        if (name.length()>=40){
            name = name.substring(0,30);
            name+=".....";
        }

        holder.name.setText(name);
        holder.locale.setText(locale);
        holder.img.setImageResource(ePInf.get(position).img);
        holder.point.setText(String.valueOf(ePInf.get(position).point));

        return rowView;
    }
    //Khởi tạo gricview gồm text và image
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
