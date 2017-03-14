package com.saran.test.daggerfirebasemvp.signin.dagger;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.saran.test.daggerfirebasemvp.signin.model.User;

/**
 * Created by core I5 on 2/20/2017.
 */

public interface SignInContract {
    interface View{
        void SuccessMsg(String msg);
        void ErrorMsg(String msg);
    }

    interface UserSign{
        boolean validateEmail(String email);
        boolean validatePassword(String password);
        boolean checkLogin();
        void getResponse(Task<AuthResult> task, User user);
        void logOut();
    }

    interface Interactor{
        void saveUser(User user);
        void clearUser();
        String getUser();
    }
}
