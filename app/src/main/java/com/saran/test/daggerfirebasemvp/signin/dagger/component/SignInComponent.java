package com.saran.test.daggerfirebasemvp.signin.dagger.component;

import com.saran.test.daggerfirebasemvp.CustomScope;
import com.saran.test.daggerfirebasemvp.sharedpref.component.SharedPrefComponent;
import com.saran.test.daggerfirebasemvp.signin.dagger.module.SignInModule;
import com.saran.test.daggerfirebasemvp.signin.view.MainActivity;

import dagger.Component;

/**
 * Created by core I5 on 2/20/2017.
 */
@CustomScope
@Component(dependencies = SharedPrefComponent.class,modules = SignInModule.class)
public interface SignInComponent {
    void inject(MainActivity activity);
}
