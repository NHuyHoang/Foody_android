package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hhoang.a14110066_nguyenhuyhoang_foody.R;

//Custom list adapter cho các list hiển thị quận và đường trong các quận
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private Map<String,List<String>> sources;
    private List<String> mHeaderGroup = new ArrayList<>();
    private List<String> mDataChild;

    public ExpandableListAdapter(Context context, Map<String,List<String>> sources){
        mContext = context;
        this.sources = sources;
        for(String key:sources.keySet()){
            mHeaderGroup.add(key);
        }
    }
    @Override
    public int getGroupCount() {
        return mHeaderGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sources.get(mHeaderGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaderGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sources.get(mHeaderGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    //group cha gồm là các tên quận
    @Override
    public View getGroupView(final int groupPosition,final boolean isExpanded, View convertView,final ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.group_layout,parent,false);
        }
        final TextView tvHeader = (TextView) convertView.findViewById(R.id.groudHeaderTV);
        tvHeader.setText(mHeaderGroup.get(groupPosition));
        String header = (String)getChild(groupPosition,0);
        int count;
        if( header==null || header.equals("") ) count = 0;
        else count = getChildrenCount(groupPosition);
        //Text view hiển thị số lượng đường trong 1 quận/huyện
        TextView tvCount = (TextView) convertView.findViewById(R.id.grCount);
        tvCount.setText(String.valueOf(count)+" đường");
        tvCount.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                else ((ExpandableListView) parent).expandGroup(groupPosition, true);
                ((MyApplication)mContext.getApplicationContext()).setDistrict(tvHeader.getText().toString());
            }
        });
        return convertView;
    }
    //list con chứa các tên đường trong 1 quận/huyện
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.child_row,parent,false);
        }
        TextView tvChild = (TextView) convertView.findViewById(R.id.childRowTv);
        tvChild.setText((String)getChild(groupPosition,childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
