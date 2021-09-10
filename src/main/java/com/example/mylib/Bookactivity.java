package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Bookactivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtDescription, txtPages;
    private Button  btnAddToCurrentRead, btnAddToFavorite,btnAddToWantToRead, btnAddToAlreadyRead;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookactivity);

        initViews();

        //TODO: Get the data from recycler view in here
       // Book book = new Book(1, "1111", "SHit me", 13, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.C6qEjB0Cx9xGGeDyoV18KQHaEb%26pid%3DApi&f=1.jpg","ambia morio", "vile iko"));

        Intent intent = getIntent();
        if ( null != intent) {
            int bookid = intent.getIntExtra("bookId", -1);

            if (bookid != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookid);
                if (null !=  incomingBook) {
                    setData(incomingBook);
                    
                    
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentReading(incomingBook);
                    handleFavorite(incomingBook);

                    //TODO CREATE  the highlighted
                }
            }
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existInwantToReadBooks = false;

        for (Book b: wantToReadBooks) {
            if(b.getId() == book.getId()) {
                existInwantToReadBooks = true;

            }
        }

        if (existInwantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        }else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(Bookactivity.this).addToWantToRead(book)) {
                        Toast.makeText(Bookactivity.this, "Book Added", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(Bookactivity.this, WantToRead.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Bookactivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }
/*
* enable and disable buttons
*Add the book to already read arraylist
*
* */
    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks) {
            if(b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;

            }
        }

        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(Bookactivity.this).addToWantToRead(book)) {
                        Toast.makeText(Bookactivity.this, "Book Added", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(Bookactivity.this, AlreadyReadBook.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(Bookactivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }


    }

    private void handleCurrentReading (Book book){
        ArrayList<Book> curentReading = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b: curentReading) {
            if(b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;

            }
        }

        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentRead.setEnabled(false);
        }else {
            btnAddToCurrentRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(Bookactivity.this).addToCurrentRead(book)) {
                        Toast.makeText(Bookactivity.this, "Book Added", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(Bookactivity.this, CurrentlyReadActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(Bookactivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }

    }

    private void handleFavorite (final Book book) {

        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

        boolean existInFavoriteBooks = false;

        for (Book b: favoriteBooks) {
            if(b.getId() == book.getId()) {
                existInFavoriteBooks= true;

            }
        }

        if (existInFavoriteBooks) {
            btnAddToFavorite.setEnabled(false);
        }else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(Bookactivity.this).addToFavorite(book)) {
                        Toast.makeText(Bookactivity.this, "Book Added", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(Bookactivity.this, FavoriteActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(Bookactivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }


    private void  setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages())); //since its an int value//
        txtDescription.setText(book.getShortDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }


    private void  initViews() {
        txtAuthor = findViewById(R.id.txtAuthor);
        txtBookName = findViewById(R.id.txtBookName);
        txtDescription = findViewById(R.id.txtShortDescrip);
        txtPages = findViewById(R.id.txtPages);


        btnAddToAlreadyRead = findViewById(R.id.btnAlreadrRead);
        btnAddToCurrentRead = findViewById(R.id.btnAddtoCurrent);
        btnAddToFavorite = findViewById(R.id.btnAddFavourites);
        btnAddToWantToRead = findViewById(R.id.btnWantRead);


        bookImage = findViewById(R.id.imgBook);
    }


}

