package com.example.joakin.sushiole;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by joaquinlopezamador on 15/11/2016.
 */

public class LoginC implements View.OnClickListener{

    Login vista;


    public LoginC(Login vista) {

        this.vista = vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.BTNSIGNIN){
            String pass = vista.ETNPassword.getText().toString();
            String email = vista.ETNUser.getText().toString();
            Dataholder.mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(vista, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            if (task.isSuccessful()){
                                Intent intent = new Intent(vista,MainActivity.class);
                                vista.startActivity(intent);
                                vista.finish();
                            }

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());

                            }

                            // ...
                        }
                    });
        }else {
            FragmentManager fm=vista.getSupportFragmentManager();
            FragmentTransaction trns= fm.beginTransaction();

            trns.show(vista.fragmentSignUp);
            trns.commit();
        }



    }
}
