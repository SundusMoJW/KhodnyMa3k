package com.twins.sundus.osama.khodnyma3k.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twins.sundus.osama.khodnyma3k.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceDataFragment extends Fragment {


    public DeviceDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_data, container, false);
    }

}
