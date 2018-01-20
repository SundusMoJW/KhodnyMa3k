package com.twins.sundus.osama.khodnyma3k.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.twins.sundus.osama.khodnyma3k.Adapter.AutoCompleteAdapter;
import com.twins.sundus.osama.khodnyma3k.Classes.PlaceAutocomplete.PlacePredictions;
import com.twins.sundus.osama.khodnyma3k.Interface.AppConstant;
import com.twins.sundus.osama.khodnyma3k.R;
import com.twins.sundus.osama.khodnyma3k.Util.UIApplication;
import com.twins.sundus.osama.khodnyma3k.Util.VolleyRequests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.GETPLACESHIT;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.GOOGLE_API_KEY;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.MY_LOCATION_REQUEST_CODE;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.PLACE_AUTOCOMPLETE_REQUEST_CODE;

public class PickLocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private EditText Address;
    private FragmentManager fragmentManager;
    private String preFilledText;
    private VolleyRequests request;
    private Handler handler;
    private double latitude;
    private double longitude;
    private ListView mAutoCompleteList;
    private PlacePredictions predictions;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private AutoCompleteAdapter mAutoCompleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_location);

        if (getIntent().hasExtra("Search Text")) {
            preFilledText = getIntent().getStringExtra("Search Text");
        }

        fragmentManager = getSupportFragmentManager();
        Address = findViewById(R.id.adressText);
        mAutoCompleteList = findViewById(R.id.searchResultLV);

        //get permission for Android M
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            fetchLocation();
        } else {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_LOCATION_REQUEST_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            } else {
                fetchLocation();
            }
        }

        //Add a text change listener to implement autocomplete functionality
        Address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(AppConstant.TAG, "beforeTextChanged");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(AppConstant.TAG, "onTextChanged");

                // optimised way is to start searching for laction after user has typed minimum 3 chars
                if (Address.getText().length() > 3) {

                    Runnable run = new Runnable() {


                        @Override
                        public void run() {

                            // cancel all the previous requests in the queue to optimise your network calls during autocomplete search
                            UIApplication.getAnInstance().cancel(GETPLACESHIT);

                            //build Get url of Place Autocomplete and hit the url to fetch result.

                            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                                @Override
                                public void onDataReceived(Object o) {
                                    predictions = (PlacePredictions) o;
                                    Log.i(AppConstant.TAG, predictions.toString());
                                    if (mAutoCompleteAdapter == null) {
                                        mAutoCompleteAdapter = new AutoCompleteAdapter(PickLocationActivity.this, predictions.getPlaces(), PickLocationActivity.this);
                                        mAutoCompleteList.setAdapter(mAutoCompleteAdapter);
                                    } else {
                                        mAutoCompleteAdapter.clear();
                                        mAutoCompleteAdapter.addAll(predictions.getPlaces());
                                        mAutoCompleteAdapter.notifyDataSetChanged();
                                        mAutoCompleteList.invalidate();
                                    }
                                }
                            }).getPlace(getPlaceAutoCompleteUrl(Address.getText().toString()));
                            //Give a tag to your request so that you can use this tag to cancle request later.
                        }
                    };

                    // only canceling the network calls will not help, you need to remove all callbacks as well
                    // otherwise the pending callbacks and messages will again invoke the handler and will send the request
                    if (handler != null) {
                        handler.removeCallbacksAndMessages(null);
                    } else {
                        handler = new Handler();
                    }
                    handler.postDelayed(run, 1000);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Address.setText(preFilledText);
        Address.setSelection(Address.getText().length());

        mAutoCompleteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // pass the result to the calling activity
                Log.i(AppConstant.TAG, "onItemClick");
                Intent intent = new Intent();
                intent.putExtra("Location Address", predictions.getPlaces().get(position).getPlaceDesc());
                setResult(PLACE_AUTOCOMPLETE_REQUEST_CODE, intent);
                finish();
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            Log.i(AppConstant.TAG, "onConnected");

            if (mLastLocation != null) {
                latitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission granted!
                    fetchLocation();

                } else {
                    // permission denied!

                    Toast.makeText(this, "Please grant permission for using this app!", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void fetchLocation() {
        //Build google API client to use fused location
        buildGoogleApiClient();

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    public String getPlaceAutoCompleteUrl(String input) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/place/autocomplete/json");
        urlString.append("?input=");
        try {
            urlString.append(URLEncoder.encode(input, "utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlString.append("&location=");
        urlString.append(latitude + "," + longitude); // append lat long of current location to show nearby results.
        urlString.append("&radius=500&language=en");
        urlString.append("&key=" + GOOGLE_API_KEY);

        Log.d("FINAL URL:::   ", urlString.toString());
        return urlString.toString();
    }
}
