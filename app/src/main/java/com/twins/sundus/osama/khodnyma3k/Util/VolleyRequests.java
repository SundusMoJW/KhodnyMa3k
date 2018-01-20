package com.twins.sundus.osama.khodnyma3k.Util;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.twins.sundus.osama.khodnyma3k.Classes.PlaceAutocomplete.PlacePredictions;
import com.twins.sundus.osama.khodnyma3k.Interface.AppConstant;

import org.json.JSONObject;

import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.GETPLACESHIT;

/**
 * Created by Osama on 1/20/2018.
 */

public class VolleyRequests {
    public interface IReceiveData {
        void onDataReceived(Object o);
    }

    private IReceiveData iReceiveData;

    public IReceiveData getIReceiveData() {
        return iReceiveData;
    }

    public VolleyRequests setIReceiveData(IReceiveData iReceiveData) {
        this.iReceiveData = iReceiveData;
        return this;
    }


    public void getPlace(final String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                PlacePredictions predictions = gson.fromJson(response.toString(), PlacePredictions.class);
                if (iReceiveData != null) {
                    iReceiveData.onDataReceived(predictions);
                }
                Log.i(AppConstant.TAG, "predictions:" + predictions.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(AppConstant.TAG, "onErrorResponse");
            }
        });
        request.setTag(GETPLACESHIT);
        UIApplication.getAnInstance().addToRequestQueue(request);
    }
}
