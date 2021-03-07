package com.dcvg.practice.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dcvg.practice.R;
import com.dcvg.practice.model.Data;

import java.util.List;

import static com.dcvg.practice.activity.MainActivity.content_data;
import static com.dcvg.practice.activity.MainActivity.id;
import static com.dcvg.practice.activity.MainActivity.status_data;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Holder> {

    private Context context;
    private List<Data> dataList;

    public DataAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.data = dataList.get(position);
        holder.tvIDData.setText(String.valueOf(holder.data.getId()));
        holder.tvContentData.setText(holder.data.getContent());
        holder.tvStatusData.setText(holder.data.getStatus());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView tvIDData;
        private TextView tvContentData;
        private TextView tvStatusData;
        private Data data;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvIDData = (TextView) itemView.findViewById(R.id.tvIDData);
            tvContentData = (TextView) itemView.findViewById(R.id.tvContentData);
            tvStatusData = (TextView) itemView.findViewById(R.id.tvStatusData);
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            id = data.getId();
            content_data = data.getContent();
            status_data = data.getStatus();
            menu.setHeaderTitle("Chọn thao tác");
            menu.add(0, 121, 1, "Xem chi tiết");
            menu.add(0, 122, 1, "Xóa");
            menu.add(0, 123, 1, "Sửa");

        }

    }


}
