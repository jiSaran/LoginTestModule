package com.saran.test.daggerfirebasemvp;

import android.app.Application;

import com.saran.test.daggerfirebasemvp.sharedpref.component.DaggerSharedPrefComponent;
import com.saran.test.daggerfirebasemvp.sharedpref.component.SharedPrefComponent;
import com.saran.test.daggerfirebasemvp.sharedpref.module.SharedPrefModule;

/**
 * Created by core I5 on 2/20/2017.
 */

public class App extends Application {
    private SharedPrefComponent mPrefComp;

    @Override
    public void onCreate() {
        super.onCreate();
        mPrefComp = DaggerSharedPrefComponent.builder()
                .sharedPrefModule(new SharedPrefModule(this))
                .build();
    }

    public SharedPrefComponent getSharedPrefComp(){
        return mPrefComp;
    }
}
