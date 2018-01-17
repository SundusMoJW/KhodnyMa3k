package com.twins.sundus.osama.khodnyma3k.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.twins.sundus.osama.khodnyma3k.Activity.AddTripActivity;
import com.twins.sundus.osama.khodnyma3k.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterDataDeviceFragment extends Fragment {


    private Button ok;

    public EnterDataDeviceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view=inflater.inflate(R.layout.fragment_enter_data_device, container, false);

                ok=view.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), AddTripActivity.class);
                        startActivity(intent);
//                        getActivity().finish();
                    }
                });
        return view;
    }
}
