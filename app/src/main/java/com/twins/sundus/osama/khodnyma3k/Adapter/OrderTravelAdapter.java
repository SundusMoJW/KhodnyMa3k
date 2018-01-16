package com.twins.sundus.osama.khodnyma3k.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.twins.sundus.osama.khodnyma3k.Classes.OrderTravel;
import com.twins.sundus.osama.khodnyma3k.R;

import java.util.ArrayList;


/**
 * Created by Osama on 12/12/2017.
 */

public class OrderTravelAdapter extends RecyclerView.Adapter<OrderTravelAdapter.ViewHolder> implements Filterable {

    //    private final OnDrawerItemClickListener listener;
    private ArrayList<OrderTravel> item = new ArrayList<>();
    private ArrayList<OrderTravel> itemFilter = new ArrayList<>();
    private Context context;

    public OrderTravelAdapter(Context context, ArrayList<OrderTravel> originlItem) {
        this.context = context;
        this.item = originlItem;
        this.itemFilter = originlItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_temp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.time.setText(item.get(position).getTime() + " am");
        holder.date.setText(item.get(position).getDate() + "");
        holder.fromTo.setText(item.get(position).getFromTo());
        holder.number.setText(item.get(position).getNumber() + "");
        holder.numSalary.setText(item.get(position).getSlary() + "ريال");

        /********************
         * 1 : go
         * 2 : back
         * 3 : bidirectional
         * *****************/
      if(item.get(position).getDirection()==1) {
          holder.image.setImageResource(R.drawable.ic_arrow);
      }else if(item.get(position).getDirection()==2){
          holder.image.setImageResource(R.drawable.ic_bi_direction);
      }else {
          holder.image.setImageResource(R.drawable.ic_arrow);
      }
//        Uri uri = Uri.parse( + item.get(position).getFilePath().replace("~", ""));
//        holder.image.setImageURI(uri);

    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(OrderTravel orderTravel) {
        item.add(orderTravel);
        notifyItemInserted(item.size());
    }

    public void removeItem(int position) {
        item.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, item.size());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    item = itemFilter;
                } else {
                    ArrayList<OrderTravel> filteredList = new ArrayList<>();
                    for (OrderTravel orderTravel : itemFilter) {
                        if (orderTravel.getFromTo().toLowerCase().contains(charString)) {
                            filteredList.add(orderTravel);
                        }
                    }
                    item = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = item;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                item = (ArrayList<OrderTravel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView time;
        TextView date;
        TextView numSalary;
        TextView fromTo;
        TextView number;
        CardView cardView;
        SwitchCompat sw;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
            image = view.findViewById(R.id.img);
            numSalary = view.findViewById(R.id.numSalary);
            fromTo = view.findViewById(R.id.fromTo);
            sw = view.findViewById(R.id.sw);
            number = view.findViewById(R.id.number);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}