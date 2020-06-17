package com.example.myfoodapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.clevertap.android.sdk.CTWebInterface;
import com.clevertap.android.sdk.CleverTapAPI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize CleverTap SDK
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

    }

    public void buttonPressed(View view) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

        //Setting Product Viewed Event
        prodViewedAction.put("Product ID", "1");
        prodViewedAction.put("Product Name", "CleverTap");
        try {
            prodViewedAction.put("Product Image", imageRetriever());
        } catch (IOException e) { e.printStackTrace();}
        clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);

        //Push Email Id to profile
        profileUpdate.put("Email", "dk+hdeshmukh1611@clevertap.com");
        clevertapDefaultInstance.onUserLogin(profileUpdate);
    }

    public Bitmap imageRetriever() throws IOException {
        URL url = new URL("https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg ");
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        return bmp;
    }

}