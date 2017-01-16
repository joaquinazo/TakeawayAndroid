package com.example.joakin.sushiole;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.google.android.gms.internal.zzs.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSignUp extends Fragment implements View.OnClickListener {
    Button BTNSignUp;
    Button BTNBack;
    EditText ETEmail;
    EditText ETPassword;
    EditText ETName;
    Login vista;

    public FragmentSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_sign_up, container, false);
        v.findViewById(R.id.fragmentSignUp);
        ETEmail = (EditText) v.findViewById(R.id.Email);
        ETPassword = (EditText) v.findViewById(R.id.Password);
        BTNSignUp = (Button) v.findViewById(R.id.BTNSIGNUPFRAG);
        BTNBack = (Button) v.findViewById(R.id.BTNBACKFRAG);
        BTNBack.setOnClickListener(this);
        BTNSignUp.setOnClickListener(this);


        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.BTNBACKFRAG) {


            FragmentManager fm = getFragmentManager();
            FragmentTransaction trns = fm.beginTransaction();
            trns.hide(this);
            trns.commit();

        } else if (v.getId() == R.id.BTNSIGNUPFRAG) {

            Dataholder.mAuth.createUserWithEmailAndPassword(ETEmail.getText().toString(), ETPassword.getText().toString())
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                          //  vista.finish();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                System.out.println("ERROR");
                            }

                            // ...
                        }
                    });
        }
    }


}
