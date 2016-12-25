package com.jack.notepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jack.notepad.R;
import com.jack.notepad.bean.Notepad;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * 记事本列表适配器
 */
public class NotepadAdapter extends BaseAdapter {

    private Context mContext;
    private List<Notepad> mData;
    private LayoutInflater mInflater;

    public NotepadAdapter(Context mContext, List<Notepad> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_notepad_list, null);
            holder = new ViewHolder();
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_notepad_list_title);
            holder.mTvTime = (TextView) convertView.findViewById(R.id.tv_notepad_list_time);
            holder.mTvContent = (TextView) convertView.findViewById(R.id.tv_notepad_list_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Notepad notepad = mData.get(position);
        holder.mTvTitle.setText(notepad.getTitle());
        holder.mTvTime.setText(notepad.getCreateTime() + "s");
        holder.mTvContent.setText(notepad.getContent());
        return convertView;
    }

    class ViewHolder{
        TextView mTvTitle;
        TextView mTvTime;
        TextView mTvContent;
    }
}
