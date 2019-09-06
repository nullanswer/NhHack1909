package com.example.nhhack1909;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhhack1909.Data.BestHouseData;

import java.util.ArrayList;

public class SRAdapter extends RecyclerView.Adapter<SRViewHolder> {

    ArrayList<BestHouseData> data = new ArrayList<>();

    @NonNull
    @Override
    public SRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sr_view, parent, false);
        return new SRViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SRViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<BestHouseData> training) {
        data = training;
        notifyDataSetChanged();
    }
}
