package com.example.joakin.sushiole;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Joakin on 10/01/2017.
 */

public class Dataholder extends FirebaseMessagingService {

    public static Dataholder instance=new Dataholder();

    FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();

    public static FirebaseAuth mAuth;
    public static com.google.firebase.auth.FirebaseUser FirebaseUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    DatabaseReference refCategorias = database.getReference("productosV2/categorias");

    ArrayList<HashMap<String,String>> categorias = new ArrayList<>();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


}
