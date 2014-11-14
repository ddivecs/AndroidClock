package com.example.darren.clock;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Matt on 11/12/2014.
 */
public class SettingsActivity extends PreferenceActivity{

        Boolean pongAni, textColor, bgColor;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

            //display fragment as main activity
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsActivityFragment())
                    .commit();

            SharedPreferences.OnSharedPreferenceChangeListener listener =
                    new SharedPreferences.OnSharedPreferenceChangeListener() {
                        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {

                            //ClockView clockView = (ClockView)findViewById(R.id.clockView);
                            pongAni = prefs.getBoolean("pongAnimation", false);
                            textColor = prefs.getBoolean("textColorChange", false);
                            bgColor = prefs.getBoolean("backgroundColorChange", false);


//                            onActivityResult(1, RESULT_OK, new Intent());

                           //System.err.println("pongAni: " + pongAni);
//
//                            clockView.setPongAnimation(pongAni);
//                            clockView.setTextColor(textColor);
//                            clockView.setBackgroundColorChange(bgColor);

                        }
                    };
            sharedPref.registerOnSharedPreferenceChangeListener(listener);
        }

    @Override
    public void onBackPressed(){

        Intent toReturn = new Intent();

        toReturn.putExtra("pongAni", pongAni);
        toReturn.putExtra("textColor", textColor);
        toReturn.putExtra("bgColor", bgColor);

        System.err.println("Added extras to toReturn");

        System.err.println("Back button pressed");
        setResult(1, toReturn);
        super.onBackPressed();
    }
}
