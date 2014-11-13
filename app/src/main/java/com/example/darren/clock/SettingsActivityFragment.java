package com.example.darren.clock;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Matt on 11/13/2014.
 */
public class SettingsActivityFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //load preferences
        addPreferencesFromResource(R.xml.preferences);
    }
}
