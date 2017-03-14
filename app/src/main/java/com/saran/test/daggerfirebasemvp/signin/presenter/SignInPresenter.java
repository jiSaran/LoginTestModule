package com.saran.test.daggerfirebasemvp.signin.presenter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.saran.test.daggerfirebasemvp.signin.dagger.SignInContract;
import com.saran.test.daggerfirebasemvp.signin.model.SignInModel;
import com.saran.test.daggerfirebasemvp.signin.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Created by core I5 on 2/20/2017.
 */

public class SignInPresenter implements SignInContract.UserSign {

    private SignInContract.View mView;
    private SignInModel signInModel;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Inject
    public SignInPresenter(SignInModel model, SignInContract.View view){
        this.mView = view;
        signInModel = model;
    }

    @Override
    public boolean checkLogin(){
        String user = signInModel.getUser();
        if(user!=null && !user.trim().isEmpty()){
            mView.SuccessMsg("Successfully signed in as "+user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void getResponse(Task<AuthResult> task, User user){
        if(task.isSuccessful()){
            mView.SuccessMsg("Successfully signed in as "+user.getEmail());
            signInModel.saveUser(user);
        } else {
            mView.ErrorMsg("Signin Error!!!");
        }
    }

    @Override
    public void logOut() {
        signInModel.clearUser();
    }

    @Override
    public boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    @Override
    public boolean validatePassword(String password) {
        if(password!=null && !password.trim().isEmpty()){
            return true;
        }
        return false;
    }
}
