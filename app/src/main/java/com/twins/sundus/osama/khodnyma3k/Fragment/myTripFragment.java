package com.twins.sundus.osama.khodnyma3k.Fragment;


import android.os.Bundle;
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
import android.widget.Toast;

import com.twins.sundus.osama.khodnyma3k.Adapter.DataTravelAdapter;
import com.twins.sundus.osama.khodnyma3k.Classes.DataTravel;
import com.twins.sundus.osama.khodnyma3k.Interface.OnDrawerItemClickListener;
import com.twins.sundus.osama.khodnyma3k.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class myTripFragment extends Fragment {

    private ArrayList<DataTravel> data;
    private DataTravelAdapter rvadapter;
    public myTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view=inflater.inflate(R.layout.fragment_my_trip, container, false);

        data=fill_with_data();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        rvadapter = new DataTravelAdapter(getActivity(), data, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int postition) {
//                Intent intent=new Intent(getActivity(), TravelActivity.class);
//                startActivity(intent);
            }
            @Override
            public void onPhotoClick(View view) {
            /************** no thing************/
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rvadapter);
        //enable search
        setHasOptionsMenu(true);

        return view;
    }

    public ArrayList<DataTravel> fill_with_data() {

        ArrayList<DataTravel> data = new ArrayList<>();
        data.add(new DataTravel("17/1/2018", "8:20" ,3,"مكة","الرياض","أسامة","الرياض",3,R.drawable.default_placeholder));
        data.add(new DataTravel("17/1/2018", "8:20",3,"مكة","الرياض","أسامة","الرياض",3,R.drawable.default_placeholder));
        data.add(new DataTravel("17/1/2018", "8:20",3,"مكة","الرياض","أسامة","الرياض",3,R.drawable.default_placeholder));
        data.add(new DataTravel("17/1/2018", "8:20",3,"مكة","الرياض","أسامة","الرياض",3,R.drawable.default_placeholder));
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
