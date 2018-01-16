package com.twins.sundus.osama.khodnyma3k.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.twins.sundus.osama.khodnyma3k.Fragment.DataTravelFragment;
import com.twins.sundus.osama.khodnyma3k.Fragment.DeviceDataFragment;
import com.twins.sundus.osama.khodnyma3k.R;

import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.COUNT;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.FRAGMENT_DATAT_RAVEL;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.FRAGMENT_DEVICE_DATA_FRAGMENT;

/**
 * Created by iSaleem on 11/18/17.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private final Context context;

    public MyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case FRAGMENT_DATAT_RAVEL:
                fragment = new DataTravelFragment();
                break;
            case FRAGMENT_DEVICE_DATA_FRAGMENT:
                fragment = new DeviceDataFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String fragment = null;
        switch (position) {
            case FRAGMENT_DATAT_RAVEL:
                fragment = context.getString(R.string.fragment_data_travel);
                break;
            case FRAGMENT_DEVICE_DATA_FRAGMENT:
                fragment = context.getString(R.string.fragment_device_data);
                break;
        }
        return fragment;
    }
}
