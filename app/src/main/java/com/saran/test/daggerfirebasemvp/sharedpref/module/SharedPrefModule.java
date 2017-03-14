package com.saran.test.daggerfirebasemvp.sharedpref.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by core I5 on 2/20/2017.
 */
@Module
public class SharedPrefModule {
    private SharedPreferences pref;

    public SharedPrefModule(Application application){
        this.pref = PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public SharedPreferences getSharedPref(){
        return pref;
    }
}
