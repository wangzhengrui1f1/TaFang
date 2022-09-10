package com.example.tafang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.tafang.R;
import com.example.tafang.dataModel.Blog;
import java.util.List;





public class BolgListAdapter extends BaseAdapter {
    private List<Blog> mList;
    private Context mContext;
    public BolgListAdapter(Context mContext, List<Blog> mList) {
        this.mContext=mContext;
        this.mList=mList;
    }

    @Override
    public int getCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mList!=null?mList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bloglist_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ViewHolder finalHolder = holder;


        return convertView;
    }




    private class ViewHolder{
        LinearLayout root,s1,s2,root2,root3,root4;
        TextView title,name,t1,t2,t3,name2;
        ImageView head,ima,ima1,ima2,ima3;

        public ViewHolder(View view) {
            if(view==null)
                return;
            root= (LinearLayout) view.findViewById(R.id.root);
            root2= (LinearLayout) view.findViewById(R.id.root2);
            root3= (LinearLayout) view.findViewById(R.id.root3);
            root4= (LinearLayout) view.findViewById(R.id.root4);
            title= (TextView) view.findViewById(R.id.title);
            head= (ImageView) view.findViewById(R.id.hs2);
            name= (TextView) view.findViewById(R.id.pn_name);
            name2= (TextView) view.findViewById(R.id.name2);

            t1= (TextView) view.findViewById(R.id.t1);
            t2= (TextView) view.findViewById(R.id.t2);
            t3= (TextView) view.findViewById(R.id.t3);

            s1= (LinearLayout) view.findViewById(R.id.s1);
            s2= (LinearLayout) view.findViewById(R.id.s2);
            ima= (ImageView) view.findViewById(R.id.imageView20);
            ima1= (ImageView) view.findViewById(R.id.imageView21);
            ima2= (ImageView) view.findViewById(R.id.imageView22);
            ima3= (ImageView) view.findViewById(R.id.imageView23);

        }
    }
}