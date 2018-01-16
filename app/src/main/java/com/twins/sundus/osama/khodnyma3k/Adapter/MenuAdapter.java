package com.twins.sundus.osama.khodnyma3k.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twins.sundus.osama.khodnyma3k.Classes.DrawerItem;
import com.twins.sundus.osama.khodnyma3k.Interface.OnDrawerItemClickListener;
import com.twins.sundus.osama.khodnyma3k.R;

import java.util.ArrayList;

import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.TYPE_FOOTER;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.TYPE_HEADER;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.TYPE_ITEM;

/**
 * Created by Osama on 1/7/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<DrawerItem> items;
    private int profile;
    private Activity context;
    private OnDrawerItemClickListener listener;
    private String name;
    private String address;
    public static int PROFILE = R.drawable.default_placeholder;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private int Holderid;
        private TextView textView;
        private ImageView imageView;
        private ImageView profile;
        private TextView Name;
        private TextView address;
        private Button goToLive;
        public LinearLayout drawer_Item;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.text_home);
                drawer_Item = (LinearLayout) itemView.findViewById(R.id.drower_Item);
                imageView = (ImageView) itemView.findViewById(R.id.img_item);
                Holderid = 1;
            } else if (ViewType == TYPE_HEADER) {
                Name = (TextView) itemView.findViewById(R.id.name);
                address = (TextView) itemView.findViewById(R.id.address);
                profile = (ImageView) itemView.findViewById(R.id.img);
                Holderid = 0;
            } else {
                goToLive = (Button) itemView.findViewById(R.id.go_to_live);
                Holderid = 2;
            }
        }

        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }
    }

    public ArrayList<DrawerItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<DrawerItem> items) {
        this.items = items;
    }

    public MenuAdapter(Activity context, ArrayList<DrawerItem> items, OnDrawerItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar_menu, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hedar_menu, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_menu, parent, false);
            ViewHolder vhFooter = new ViewHolder(v, viewType);
            return vhFooter;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, final int position) {

        if (holder.Holderid == 0) {
//            NAME = sharedPref.getString(USER_NAME_SHARED_PREF);
            name = "أسامة";
            if (name.equals("null")) {
                name = "Your Name";
            }
//            EMAILProfile = sharedPref.getString(EMAIL_SHARED_PREF);
            address = "قطاع غزة - غزة";
            if (address.equals("null")) {
                address = "Your Address";
            }
            holder.profile.setImageResource(PROFILE);
            holder.Name.setText(name);
            holder.address.setText(address);
            holder.profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPhotoClick(v);
                }
            });

        } else if (holder.Holderid == 1) {
            DrawerItem item = items.get(position - 1);//0 is header
            holder.textView.setText(item.getTitle());
            if (item.isSelected()) {
                holder.textView.setTextColor(Color.parseColor("#000000"));
                holder.drawer_Item.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.imageView.setImageResource(item.getImage_on());
            } else {
                holder.textView.setTextColor(Color.parseColor("#686D6F"));
                holder.drawer_Item.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.imageView.setImageResource(item.getImage());
            }
            holder.drawer_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position - 1);
                }
            });
        } else if (holder.Holderid == 2) {
            holder.goToLive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    listener.onButtonClick(view);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return items.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        int type=0;
        if(position==0){
          type=  TYPE_HEADER;
        }else {
            type=TYPE_ITEM;
        }
//        else  if (position>4){
//            type=TYPE_FOOTER;
//        }
        return type;
    }

}
