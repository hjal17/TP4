package com.fsm.tp4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPreferenceFragment extends PreferenceFragment {
    EditTextPreference titre,login,password;
    CheckBoxPreference use_login_password_checkbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        Bundle params = getArguments();

        titre = (EditTextPreference) findPreference("title");
        login = (EditTextPreference) findPreference("login");
        password = (EditTextPreference) findPreference("password");
        use_login_password_checkbox = (CheckBoxPreference) findPreference("useLoginPassword");
        titre.setSummary(params.getString("appName"));



    }


}
