package com.twins.sundus.osama.khodnyma3k.Fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.twins.sundus.osama.khodnyma3k.Adapter.OrderTravelAdapter;
import com.twins.sundus.osama.khodnyma3k.Classes.OrderTravel;
import com.twins.sundus.osama.khodnyma3k.R;
import com.twins.sundus.osama.khodnyma3k.Util.FragmentsUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    private ArrayList<OrderTravel> data;
    private FloatingActionButton fab;
    private TextView tv_title;
    private OrderTravelAdapter rvadapter;
    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        fab = view.findViewById(R.id.fab);
        tv_title = getActivity().findViewById(R.id.tv_title);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.addFragment(getActivity(), R.id.frame_layout, new EnterDataDeviceFragment());
                tv_title.setText(getResources().getText(R.string.enter_data_device));
            }
        });
        data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
       rvadapter = new OrderTravelAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rvadapter);
        //enable search
        setHasOptionsMenu(true);

        return view;
    }

    public ArrayList<OrderTravel> fill_with_data() {

        ArrayList<OrderTravel> data = new ArrayList<>();
        data.add(new OrderTravel(010, 123, 3, "من الرياض الى مكة", 3, true, 1));
        data.add(new OrderTravel(010, 123, 3, "من الرياض الى مكة", 3, true, 2));
        data.add(new OrderTravel(010, 123, 3, "من الرياض الى مكة", 3, true, 3));
        data.add(new OrderTravel(010, 123, 3, "من الرياض الى مكة", 3, true, 2));
        data.add(new OrderTravel(010, 123, 3, "من الرياض الى مكة", 3, true, 1));
        data.add(new OrderTravel(010, 123, 3, "من الرياض الى مكة", 3, true, 3));
        return data;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
//        if (searchView != null) {
//            searchView.setOnQueryTextListener(search);}

//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(rvadapter != null){
                    rvadapter.getFilter().filter(newText);
                }
                else{
                    if (rvadapter==null) Toast.makeText(getActivity(), "No Item", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
}
