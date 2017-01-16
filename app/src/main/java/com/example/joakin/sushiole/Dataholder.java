package com.example.joakin.sushiole;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joakin on 10/01/2017.
 */

public class Dataholder {

    public static Dataholder instance=new Dataholder();


    public static FirebaseAuth mAuth;
    public static com.google.firebase.auth.FirebaseUser FirebaseUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    DatabaseReference refCategorias = database.getReference("productosV2/categorias");

    ArrayList<HashMap<String,String>> categorias = new ArrayList<>();


}
