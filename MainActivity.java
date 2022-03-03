package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText getid, getname, getsurname, getcource;
    Button insert, update, delete, retrave;
    String id, name, surname, cource;
    RecyclerView recyclerView;
    MyDatabase myDatabase;
     List<ModalData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getid = findViewById(R.id.id);
        getname = findViewById(R.id.name);
        getsurname = findViewById(R.id.surname);
        getcource = findViewById(R.id.course);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        retrave = findViewById(R.id.retrave);
        recyclerView = findViewById(R.id.recycler);

        bindView();





    }

    private void bindView() {


        myDatabase = new MyDatabase(this);



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // myDatabase.insertData(name,surname,cource);


                // String id=getid.getText().toString();
                String name = getname.getText().toString();
                String surname = getsurname.getText().toString();
                String cource = getcource.getText().toString();


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(cource)) {
                    Toast.makeText(MainActivity.this, "Please Enter All Data", Toast.LENGTH_SHORT).show();
                } else {
                    myDatabase.insertData(MainActivity.this, name, surname, cource);}

                dataList.clear();

                Cursor cursor = myDatabase.getData();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    id = cursor.getString(0);
                    name = cursor.getString(1);
                    surname = cursor.getString(2);
                    cource = cursor.getString(3);

                    ModalData modelData = new ModalData(id, name, surname, cource);
                    dataList.add(modelData);

                    cursor.moveToNext();


                    // Log.e("datalist","a");
                    //  Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();



                }
                RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(manager);
                MyAdapter myAdapter = new MyAdapter(MainActivity.this, dataList);
                recyclerView.setAdapter(myAdapter);


            }
        });

//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                id = getid.getText().toString();
//                name = getname.getText().toString();
//                surname = getsurname.getText().toString();
//                cource = getcource.getText().toString();
//                if (TextUtils.isEmpty(id)) {
//                    Toast.makeText(MainActivity.this, "Enter id is Compulsory", Toast.LENGTH_SHORT).show();
//                } else {
//                   // myDatabase.updateData(MainActivity.this, id, name, surname, cource);
//                }
//            }
//        });
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                id = getid.getText().toString();
//                if (TextUtils.isEmpty(id)) {
//                    Toast.makeText(MainActivity.this, "Enter id is Compulsory", Toast.LENGTH_SHORT).show();
//                } else {
//                    myDatabase.deleteData(MainActivity.this, id);
//                }
//            }
//        });




//        retrave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//            }
//        });
    }
}