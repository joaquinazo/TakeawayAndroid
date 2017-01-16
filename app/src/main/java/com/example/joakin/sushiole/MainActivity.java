package com.example.joakin.sushiole;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {
    ArrayList<HashMap<String, Objects>> cats;
    ArrayList<String> catsarray = new ArrayList<>();
    ArrayList<String> urlArray = new ArrayList<>();


    ListView list;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadCategories();
        list = (ListView) findViewById(R.id.list);
        //imageView = (ImageView)findViewById(R.id.imagen);
        //dwnImages();

        // list.setAdapter(adapter);
    }

    public void downloadCategories() {
        Dataholder.instance.refCategorias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                cats = (ArrayList<HashMap<String, Objects>>) dataSnapshot.getValue();
                cats.remove(0);
                for (int i = 0; i < cats.size(); i++) {
                    System.err.println("hey i= " + i + " Value is: " + cats.get(i).get("nombre"));
                }
                array();
                settingAdapter();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hey", "Failed to read value.", error.toException());
            }
        });
    }

    public void array() {
        for (int i = 0; i < cats.size(); i++) {
            catsarray.add(String.valueOf(cats.get(i).get("nombre")));
            urlArray.add(String.valueOf(cats.get(i).get("url")));
        }
        System.out.println(catsarray.size());
    }

    public void settingAdapter() {

        list.setAdapter(new dataListAdapter(catsarray, urlArray));
    }

    class dataListAdapter extends BaseAdapter {
        ArrayList<String> Title;
        ArrayList<String> imge;

        dataListAdapter() {
            Title = null;
            imge = null;
        }

        public dataListAdapter(ArrayList<String> text, ArrayList<String> text3) {
            Title = text;
            imge = text3;

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return Title.size();
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.celda, parent, false);
            TextView title;
            ImageView i1;
            title = (TextView) row.findViewById(R.id.title);
            i1 = (ImageView) row.findViewById(R.id.img);
            System.out.println("aslldsalsldsalasdl" + catsarray.get(position));
            title.setText(catsarray.get(position));
            Picasso.with(getBaseContext()).load(urlArray.get(position)).into(i1);

            return (row);
        }
    }
}



