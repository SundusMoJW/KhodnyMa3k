package com.twins.sundus.osama.khodnyma3k.Util;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.twins.sundus.osama.khodnyma3k.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Osama on 1/7/2018.
 */

public class UIApplication extends Application {
    public static UIApplication anInstance = null;
    RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/JF_Flat_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        /************ Fresco **********************/
        Fresco.initialize(this);
        /********** Initial Volley **************/
        anInstance = this;
        requestQueue = Volley.newRequestQueue(this);
    }

    public synchronized void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }

    public void cancel(String tag) {
        requestQueue.cancelAll(tag);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static UIApplication getAnInstance() {
        return anInstance;
    }
}
