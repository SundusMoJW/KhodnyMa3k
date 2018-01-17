package com.twins.sundus.osama.khodnyma3k.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.twins.sundus.osama.khodnyma3k.Interface.AppConstant;
import com.twins.sundus.osama.khodnyma3k.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.GO_LOCATION;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.KEY_LOCATION;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.MAP_ACTIVITY_GO_REQUEST;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.MAP_ACTIVITY_GO_RESULT;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.MAP_ACTIVITY_SELECT_REQUEST;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.MAP_ACTIVITY_SELECT_RESULT;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.MY_LOCATION;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.PLACE_AUTOCOMPLETE_REQUEST2_CODE;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.PLACE_AUTOCOMPLETE_REQUEST_CODE;
import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.TAG;

public class SearchActivity extends AppCompatActivity {
    private ImageView back;
    private EditText select_location;
    private EditText want_go;
    private ImageView ic_select_location;
    private ImageView ic_want_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        select_location = findViewById(R.id.select_location);
        want_go = findViewById(R.id.want_go);
        back = findViewById(R.id.back);
        ic_select_location = findViewById(R.id.ic_select_location);
        ic_want_go = findViewById(R.id.ic_want_go);
        select_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(SearchActivity.this,NewSearchActivity.class);
//                startActivity(intent);

                try {
                    AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                            .build();

                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .setFilter(typeFilter)
                                    .build(SearchActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.i(TAG, "error from ooglePlayServicesRepairableException");
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.i(TAG, "error from GooglePlayServicesNotAvailableException");
                }
            }
        });

        want_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                            .build();

                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .setFilter(typeFilter)
                                    .build(SearchActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST2_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.i(TAG, "error from ooglePlayServicesRepairableException");
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.i(TAG, "error from GooglePlayServicesNotAvailableException");
                }
            }
        });
        ic_select_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, NewSearchActivity.class);
                intent.putExtra(KEY_LOCATION, MY_LOCATION);
                startActivityForResult(intent, MAP_ACTIVITY_SELECT_RESULT);
            }
        });
        ic_want_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, NewSearchActivity.class);
                intent.putExtra(KEY_LOCATION, GO_LOCATION);
                startActivityForResult(intent, MAP_ACTIVITY_GO_RESULT);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLACE_AUTOCOMPLETE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    select_location.setText(place.getName());
                    Log.i(TAG, "Place: " + place.getName());
                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    // TODO: Handle the error.
                    Log.i(TAG, status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                    Log.i(TAG, " The user canceled the operation");
                }
                break;
            case PLACE_AUTOCOMPLETE_REQUEST2_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    want_go.setText(place.getName());
                    Log.i(TAG, "Place: " + place.getName());
                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    // TODO: Handle the error.
                    Log.i(TAG, status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                    Log.i(TAG, " The user canceled the operation");
                }

                break;
            case MAP_ACTIVITY_SELECT_REQUEST:
                if (resultCode == MAP_ACTIVITY_SELECT_RESULT) {
                    String returnedValue = data.getStringExtra(AppConstant.RETURN_VALUE);
                    select_location.setText(returnedValue);
                }
                break;
            case MAP_ACTIVITY_GO_REQUEST:
                if (requestCode == MAP_ACTIVITY_GO_RESULT) {
                    String returnedValue = data.getStringExtra(AppConstant.RETURN_VALUE);
                    want_go.setText(returnedValue);
                }
                break;
        }
    }
}

