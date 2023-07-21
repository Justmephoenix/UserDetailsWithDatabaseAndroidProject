package com.example.sqliteapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     EditText name=(EditText) findViewById(R.id.name);
     EditText contact=(EditText) findViewById(R.id.contact);
     EditText dob=(EditText) findViewById(R.id.dob);
     Button inert=(Button) findViewById(R.id.btninsert);
     Button update=(Button) findViewById(R.id.btnupdate);
     Button delte=(Button) findViewById(R.id.btndelete);
     Button view=(Button) findViewById(R.id.btnview);
     DBHelper DB =new DBHelper(this);
     inert.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String nameTxt=name.getText().toString();
             String contactTxt=contact.getText().toString();
             String dobTxt=dob.getText().toString();

             Boolean checkinsertdata=DB.insertuserdata(nameTxt,contactTxt,dobTxt);
             if(checkinsertdata==true){
                 Toast.makeText(MainActivity.this,"Entry is inserted.",Toast.LENGTH_LONG).show();}
             else
                 Toast.makeText(MainActivity.this,"Entry is not inserted.",Toast.LENGTH_LONG).show();

         }
     });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString();
                String contactTxt=contact.getText().toString();
                String dobTxt=dob.getText().toString();

                Boolean checkupdatedata=DB.updateuserdata(nameTxt,contactTxt,dobTxt);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this,"Entry is updated..",Toast.LENGTH_LONG).show();}
                else
                    Toast.makeText(MainActivity.this,"Entry is not updtaed..",Toast.LENGTH_LONG).show();

            }
        });


        delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString();


                Boolean checkdeletedata=DB.deleteuserdata(nameTxt);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this,"Entry is deleted.",Toast.LENGTH_LONG).show();}
                else
                    Toast.makeText(MainActivity.this,"Entry is not deleted..",Toast.LENGTH_LONG).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"Entry is found.",Toast.LENGTH_LONG).show();
                    return ;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+ res.getString(0)+"\n");
                    buffer.append("Contact :"+ res.getString(1)+"\n");
                    buffer.append("Date Of Birth :"+ res.getString(2)+"\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

}
}