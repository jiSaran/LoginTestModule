package com.saran.test.daggerfirebasemvp.signin.model;

/**
 * Created by core I5 on 2/20/2017.
 */

public class User {
    private String email;
    private String password;

   public User(String email, String password){
       this.email = email;
       this.password = password;
   }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
