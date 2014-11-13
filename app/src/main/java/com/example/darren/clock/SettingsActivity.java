package com.example.darren.clock;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Matt on 11/12/2014.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //display fragment as main activity
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Fragment())
                .commit();
    }
}
