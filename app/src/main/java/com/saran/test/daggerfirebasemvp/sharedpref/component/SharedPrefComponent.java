package com.saran.test.daggerfirebasemvp.sharedpref.component;

import android.content.SharedPreferences;

import com.saran.test.daggerfirebasemvp.sharedpref.module.SharedPrefModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by core I5 on 2/20/2017.
 */
@Singleton
@Component(modules = SharedPrefModule.class)
public interface SharedPrefComponent {
    SharedPreferences getSharedPref();
}
