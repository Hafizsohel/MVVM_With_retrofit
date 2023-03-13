package com.example.mvvmget.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmget.Model.GetModel;
import com.example.mvvmget.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private Context context;
    private List<GetModel> getList;

    public MyAdapter(Context context, List<GetModel> getList) {
        this.context = context;
        this.getList = getList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Text_UserId, Text_Id, Text_Title, Text_Body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Text_UserId = itemView.findViewById(R.id.Text_userId);
            Text_Id = itemView.findViewById(R.id.Text_id);
            Text_Title = itemView.findViewById(R.id.Text_title);
            Text_Body = itemView.findViewById(R.id.Text_body);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        GetModel getModel = getList.get(position);

        holder.Text_UserId.setText("userId: " + getModel.getUserId());
        holder.Text_Id.setText("id: " + getModel.getId());
        holder.Text_Title.setText("title: " + getModel.getTitle());
        holder.Text_Body.setText("body: " + getModel.getBody());


    }

    @Override
    public int getItemCount() {
        return getList.size();
    }

    public void setPostList(List<GetModel> getList) {
        this.getList = getList;
        notifyDataSetChanged();

    }
}
