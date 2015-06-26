package com.lpdw.zurvivescompanion.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.response.EquipmentsReponse;

import java.util.ArrayList;

/**
 * Created by CAJUSTE Alain on 26/06/2015.
 */
public class EquipmentsRecyclerViewAdapter extends RecyclerView.Adapter<EquipmentsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<EquipmentsReponse.Equipments> mEquipments;
    private Context mContext;


    public EquipmentsRecyclerViewAdapter(Context context,ArrayList<EquipmentsReponse.Equipments> equipments) {
        this.mContext = context;
        this.mEquipments = equipments;
    }

    @Override
    public EquipmentsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipments_item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mEquipments.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mEquipments.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.equipments_item_row_name);
        }

    }
}
