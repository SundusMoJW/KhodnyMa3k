package com.twins.sundus.osama.khodnyma3k.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.twins.sundus.osama.khodnyma3k.Classes.DataTravel;
import com.twins.sundus.osama.khodnyma3k.R;

import java.util.ArrayList;


/**
 * Created by Osama on 12/12/2017.
 */

public class DataTravelAdapter extends RecyclerView.Adapter<DataTravelAdapter.ViewHolder> implements Filterable {

    //    private final OnDrawerItemClickListener listener;
    private ArrayList<DataTravel> item = new ArrayList<>();
    private ArrayList<DataTravel> itemFilter = new ArrayList<>();
    private Context context;

    public DataTravelAdapter(Context context, ArrayList<DataTravel> originlItem) {
        this.context = context;
        this.item = originlItem;
        this.itemFilter = originlItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trvel_temp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.time.setText(item.get(position).getTime() + " am");
        holder.date.setText(item.get(position).getDate()+"");
        holder.userAddress.setText(item.get(position).getAddress() );
        holder.fromTo.setText(item.get(position).getFromTo());
        holder.name.setText(item.get(position).getName());
        holder.number.setText(item.get(position).getNumber() + "");
        holder.numSalary.setText(item.get(position).getSlary() + "ريال");
        holder.image.setImageResource(R.drawable.default_placeholder);

//        Uri uri = Uri.parse( + item.get(position).getFilePath().replace("~", ""));
//        holder.image.setImageURI(uri);

    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(DataTravel news) {
        item.add(news);
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
                    ArrayList<DataTravel> filteredList = new ArrayList<>();
                    for (DataTravel dataTravel : itemFilter) {
                        if (dataTravel.getFromTo().toLowerCase().contains(charString)) {
                            filteredList.add(dataTravel);
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
                item = (ArrayList<DataTravel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView time;
        TextView date;
        TextView name;
        TextView userAddress;
        TextView numSalary;
        TextView fromTo;
        TextView number;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
            image = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            userAddress = view.findViewById(R.id.userAddress);
            numSalary = view.findViewById(R.id.numSalary);
            fromTo = view.findViewById(R.id.fromTo);
            number = view.findViewById(R.id.number);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}