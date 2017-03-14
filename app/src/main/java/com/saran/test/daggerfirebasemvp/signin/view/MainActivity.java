package com.saran.test.daggerfirebasemvp.signin.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.saran.test.daggerfirebasemvp.App;
import com.saran.test.daggerfirebasemvp.R;
import com.saran.test.daggerfirebasemvp.signin.dagger.SignInContract;
import com.saran.test.daggerfirebasemvp.signin.dagger.component.DaggerSignInComponent;
import com.saran.test.daggerfirebasemvp.signin.dagger.module.SignInModule;
import com.saran.test.daggerfirebasemvp.signin.model.User;
import com.saran.test.daggerfirebasemvp.signin.presenter.SignInPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SignInContract.View {

    @BindView(R.id.loginbtn)
    Button login_btn;

    @BindView(R.id.logoutbtn)
    Button logout_btn;

    @BindView(R.id.status_txt)
    TextView status_txt;

    @BindView(R.id.email)
    EditText email_txt;

    @BindView(R.id.password)
    EditText password_txt;

    @Inject
    SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        logout_btn.setVisibility(View.GONE);

        DaggerSignInComponent.builder()
                .signInModule(new SignInModule(this))
                .sharedPrefComponent(((App)getApplicationContext()).getSharedPrefComp())
                .build().inject(this);

        presenter.checkLogin();

    }

    @OnClick(R.id.loginbtn)
    void LogIn(){
        final String email = email_txt.getText().toString();
        final String password = password_txt.getText().toString();
        if(presenter.validateEmail(email)){
            if(presenter.validatePassword(password)){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                User user = new User(email,password);
                                presenter.getResponse(task,user);
                            }
                        });
            } else {
                Toast.makeText(this,"Password length is 0",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Invalid email",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.logoutbtn)
    void LogOut(){
        FirebaseAuth.getInstance().signOut();
        presenter.logOut();
        login_btn.setVisibility(View.VISIBLE);
        logout_btn.setVisibility(View.GONE);
        status_txt.setVisibility(View.GONE);
        email_txt.setVisibility(View.VISIBLE);
        password_txt.setVisibility(View.VISIBLE);
    }

    @Override
    public void SuccessMsg(String msg) {
        status_txt.setText(msg);
        login_btn.setVisibility(View.GONE);
        logout_btn.setVisibility(View.VISIBLE);
        status_txt.setVisibility(View.VISIBLE);
        email_txt.setVisibility(View.GONE);
        password_txt.setVisibility(View.GONE);
    }

    @Override
    public void ErrorMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
