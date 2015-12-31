package app.bicintime.wolf.navdrawer;

/**
 * Created by wolf on 12/31/2015.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unchecked")
public class NewAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem, tempChild;
    public ArrayList<Object> Childtem = new ArrayList<Object>();
    public LayoutInflater minflater;
    public Activity activity;
    private final Context context;

    public NewAdapter(Context context,ArrayList<String> grList, ArrayList<Object> childItem) {
        this.context = context;
        groupItem = grList;
        this.Childtem = childItem;
    }

    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
        activity = act;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = new TextView(context);
        }
        text = (TextView) convertView;
        text.setText(">"+tempChild.get(childPosition));
//		convertView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(activity, tempChild.get(childPosition),
//						Toast.LENGTH_SHORT).show();
//			}
//		});
        convertView.setTag(tempChild.get(childPosition));
        return convertView;
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) Childtem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
        }
        ((TextView) convertView).setText(groupItem.get(groupPosition));
        convertView.setTag(groupItem.get(groupPosition));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}