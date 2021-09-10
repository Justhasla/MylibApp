package com.example.mylib;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAllbooks, btnCurent,btnAlready,
            btnwant,btnFav,btnAbout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        btnAllbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);

                //intent-object can be written any way

            }
        });

        btnAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBook.class);
                startActivity(intent);
            }
        });

        btnCurent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadActivity.class);
                startActivity(intent);
            }
        });

        btnwant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WantToRead.class);
                startActivity(intent);
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("when thing rie they may fall");

                /*set 2 buttons
                1.implementing onclick
                 */

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO; Show website

                        Intent intent =  new Intent(MainActivity.this, WebsiteActivity.class);
                        intent.putExtra("url", "https://pornhub.com/");
                        startActivity(intent);
                    }
                });


                builder.create().show();

            }
        });


        Utils.getInstance(this);




    }

    private  void initViews(){

        btnAllbooks = findViewById(R.id.btnAllbooks);
        btnCurent = findViewById(R.id.btnCurent);
        btnAlready = findViewById(R.id.btnAlready);
        btnwant =findViewById(R.id.btnwant);
        btnFav = findViewById(R.id.btnFav);
        btnAbout = findViewById(R.id.btnAbout);



    }

}