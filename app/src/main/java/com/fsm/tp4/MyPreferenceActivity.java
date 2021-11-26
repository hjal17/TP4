package com.fsm.tp4;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MyPreferenceActivity extends AppCompatActivity  {
    MyPreferenceFragment fragment;
    String titre;

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setTitle("Preferences utilisateur");
        Intent intent = getIntent();
        String APP_NAME = intent.getStringExtra("appName");
        Bundle params = new Bundle();
        params.putString("appName",APP_NAME);



        fragment = new MyPreferenceFragment();
        fragment.setArguments(params);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content,fragment).commit();





    }




    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent i = new Intent();
        i.putExtra("m", 13);
        setResult(RESULT_OK, i);
        finish();

    }

}
