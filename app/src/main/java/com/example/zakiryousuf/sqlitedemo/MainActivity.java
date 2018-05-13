package com.example.zakiryousuf.sqlitedemo;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText name,surname,marks,id;
    Button btnadd;
    Button btnview;
    Button btnupdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB=new DatabaseHelper(this);
        btnadd=(Button)findViewById(R.id.btnadd);
        btnupdate=(Button)findViewById(R.id.btnupdate);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        surname=(EditText)findViewById(R.id.surname);
        marks=(EditText)findViewById(R.id.marks);
        btnview=(Button) findViewById(R.id.btnview);
        btnDelete=(Button)findViewById(R.id.btndelete);
        addData();
        viewAll();
        updatedata();
        deleteData();
    }
    public void viewAll(){
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDB.getAllData();
                if(res.getCount()==0){
                    showMessage("Error","No data Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("NAME :"+res.getString(1)+"\n");
                    buffer.append("SURNAME :"+res.getString(2)+"\n");
                    buffer.append("MARKS :"+res.getString(3)+"\n\n");
                }
                //show all the data
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    public void addData(){
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean inserted= myDB.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
              if(inserted==true)
              {
                  Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
              }
              else{
                  Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
              }

            }
        });
    }
    public void updatedata(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean isupdate=myDB.updatedata(id.getText().toString(),
                                            name.getText().toString(),
                                            surname.getText().toString(),
                                            marks.getText().toString());
                if(isupdate==true)
                {
                    Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data is not updated",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isDelete = myDB.deleteData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isDelete==true)
                {
                    Toast.makeText(MainActivity.this,"Data Delete Successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data is no deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
