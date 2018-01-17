package com.twins.sundus.osama.khodnyma3k.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.twins.sundus.osama.khodnyma3k.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RequestActivity extends AppCompatActivity {

    private Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_request);
        request = findViewById(R.id.request);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RequestActivity.this, TravelActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
