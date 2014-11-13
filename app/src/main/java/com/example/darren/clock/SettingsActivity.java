package com.example.darren.clock;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by Matt on 11/12/2014.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            //display fragment as main activity
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsActivityFragment())
                    .commit();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            ClockView clockView = (ClockView)findViewById(R.id.clockView);
            Boolean pongAni = sharedPreferences.getBoolean("pongAnimation", false);
            Boolean textColor = sharedPreferences.getBoolean("textColorChange", false);
            Boolean bgColor = sharedPreferences.getBoolean("backgroundColorChange", false);

            clockView.setPongAnimation(pongAni);
            clockView.setTextColor(textColor);
            clockView.setBackgroundColorChange(bgColor);

        }
}
