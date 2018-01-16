package com.twins.sundus.osama.khodnyma3k.Util;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.twins.sundus.osama.khodnyma3k.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Osama on 1/7/2018.
 */

public class UIApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/JF_Flat_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        /************ Fresco **********************/
        Fresco.initialize(this);
        /************CalligraphyConfig*************/
    }
}
