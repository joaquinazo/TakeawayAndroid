package com.example.joakin.sushiole;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.data.DataHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.android.gms.internal.zzs.TAG;

public class Login extends AppCompatActivity {
    LoginC loginC;
    Button BTNSignUp;
    Button BTNSignIn;
    EditText ETNUser;
    EditText ETNPassword;
    FragmentSignUp fragmentSignUp;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Dataholder.mAuth= FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Dataholder.FirebaseUser = user;

                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        LoginC loginC = new LoginC(this);
        BTNSignUp = (Button) findViewById(R.id.BTNSIGNUP);
        BTNSignIn = (Button) findViewById(R.id.BTNSIGNIN);
        ETNUser = (EditText) findViewById(R.id.ETUSER);
        ETNPassword = (EditText) findViewById(R.id.ETPASSWORD);
        BTNSignIn.setOnClickListener(loginC);
        BTNSignUp.setOnClickListener(loginC);

        fragmentSignUp=(FragmentSignUp)getSupportFragmentManager().findFragmentById(R.id.fragmentSignUp);
        fragmentSignUp.getView().setBackgroundColor(Color.WHITE);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction trns= fm.beginTransaction();
        trns.hide(fragmentSignUp);
        trns.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dataholder.mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            Dataholder.mAuth.removeAuthStateListener(mAuthListener);
        }
    }



}
