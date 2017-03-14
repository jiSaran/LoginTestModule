package com.saran.test.daggerfirebasemvp.signin.model;

import android.content.SharedPreferences;

import com.saran.test.daggerfirebasemvp.signin.dagger.SignInContract;

import javax.inject.Inject;

/**
 * Created by core I5 on 3/13/2017.
 */

public class SignInModel implements SignInContract.Interactor {


    public SharedPreferences mPref;

    public SignInModel(SharedPreferences preferences){
        this.mPref = preferences;
    }

    @Override
    public void saveUser(User user) {
        mPref.edit().putString("email",user.getEmail()).commit();
        mPref.edit().putString("password",user.getPassword()).commit();
    }

    @Override
    public void clearUser() {
        mPref.edit().clear().commit();
    }

    @Override
    public String getUser() {
        if(this.mPref!=null){
            return mPref.getString("email","");
        } else {
            return null;
        }
    }

}
