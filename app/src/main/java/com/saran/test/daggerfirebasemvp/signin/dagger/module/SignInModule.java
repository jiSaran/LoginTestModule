package com.saran.test.daggerfirebasemvp.signin.dagger.module;

import android.content.SharedPreferences;

import com.saran.test.daggerfirebasemvp.CustomScope;
import com.saran.test.daggerfirebasemvp.signin.dagger.SignInContract;
import com.saran.test.daggerfirebasemvp.signin.model.SignInModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by core I5 on 2/20/2017.
 */
@Module
public class SignInModule {
    private SignInContract.View mView;

    public SignInModule(SignInContract.View view){
        this.mView = view;
    }

    @Provides
    @CustomScope
    public SignInContract.View getSignInContractView(){
        return mView;
    }

    @Provides
    @CustomScope
    public SignInModel getSignInModel(SharedPreferences prefs){
        return new SignInModel(prefs);
    }

}
