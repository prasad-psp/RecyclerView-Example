package com.psp.recyclerview_example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psp.recyclerview_example.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final MyAdapterListener listener;
    private final Context context;

    private final ArrayList<String> list;

    public MyAdapter(Context context,MyAdapterListener listener) {
        this.listener = listener;
        this.context = context;
        list = new ArrayList<>();
    }

    public boolean addItem(String msg) {
        if(!msg.isEmpty()) {
            list.add(msg);
            notifyItemInserted(list.indexOf(msg));
            return true;
        }
        return false;
    }

    public void addAllItems(ArrayList<String> list) {
        if(list != null && list.size() > 0) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        if(list.size() > position) {
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        }
    }

    public boolean clear() {
        if(list.size() > 0) {
            list.clear();
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int index = holder.getAdapterPosition();

        String msg = list.get(index);

        holder.txtMsg.setText(msg);

        holder.txtMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(msg, index);
            }
        });

        holder.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemInfoClick(msg, index);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemDeleteClick(msg, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface MyAdapterListener {
        void onItemClick(String msg,int position);
        void onItemInfoClick(String msg,int position);
        void onItemDeleteClick(String msg,int position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtMsg;
        ImageView imgInfo,imgDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMsg = itemView.findViewById(R.id.txtMsg);
            imgInfo = itemView.findViewById(R.id.imgInfo);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
