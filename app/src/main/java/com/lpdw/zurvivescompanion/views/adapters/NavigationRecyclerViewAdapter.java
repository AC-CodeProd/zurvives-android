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
import com.lpdw.zurvivescompanion.data.User;

/**
 * Created by CAJUSTE Alain on 01/06/2015.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<NavigationRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String mNavTitles[];
    private int mIcons[];
    private User mUser;
    private Context mContext;

    private IViewHolderClicks mListener;

    public NavigationRecyclerViewAdapter(Context context, String titles[], User user, IViewHolderClicks mListener) {
        this.mNavTitles = titles;
        this.mUser = user;
        this.mContext = context;
        this.mListener = mListener;
    }

    public NavigationRecyclerViewAdapter(String titles[], int icons[]) {
        this.mNavTitles = titles;
        this.mIcons = icons;
    }


    @Override
    public NavigationRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item_row, parent, false);
            ViewHolder item = new ViewHolder(v, viewType);
            return item;

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_drawer_header, parent, false);
            ViewHolder header = new ViewHolder(v, viewType);
            return header;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NavigationRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder.holderid == 1) {
            holder.textView.setText(mNavTitles[position - 1]);
            //holder.imageView.setImageResource(mIcons[position -1]);
        } else {
            //holder.profile.setImageResource(profile);
            holder.name.setText(mUser.getNickname());
            holder.email.setText(mUser.getEmail());
            holder.logout.setOnClickListener(this);
        }
    }


    /*public void setListener(IViewHolderClicks mListener){
        this.mListener = mListener;
    }*/

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logout) {
            mListener.onLogoutClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holderid;

        TextView textView;
        ImageView imageView;
        ImageView picture;
        TextView name;
        TextView email;
        ImageButton logout;


        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderid = 1;
            } else {
                name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                picture = (ImageView) itemView.findViewById(R.id.circleView);
                logout = (ImageButton) itemView.findViewById(R.id.logout);
            }
        }

    }

    public static interface IViewHolderClicks {
        public void onLogoutClick(View view);
    }
}
