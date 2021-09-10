package com.example.mylib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class  BookRecViewAdapter extends  RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>{
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>(); //reads list of all books in activity//
    private Context mContext;
    private  String parentActivity;


    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    public BookRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent, false);



        return  new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull  BookRecViewAdapter.ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        holder.txtName.setText(books.get(position).getName() );

        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , Bookactivity.class);
                intent.putExtra("bookId", books.get(position).getId());
                intent.putExtra("bookName", books.get(position).getName());
                mContext.startActivity(intent);
            }
        });
        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());



        if (books.get(position).isExpanded()){

            TransitionManager.beginDelayedTransition(holder.parent);

            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {

                holder.btnDelete.setVisibility(View.GONE);
            }else if (parentActivity.equals("alreadyRead")){

                holder.btnDelete.setVisibility(View.VISIBLE);

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //for confirmation before deleting//
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        builder.setMessage("Are u sure u want to delete" + books.get(position).getName() + "?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromAlreadyRead(books.get(position))) {
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });

            }else if (parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //for confirmation before deleting//
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        builder.setMessage("Are u sure u want to delete" + books.get(position).getName() + "?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromWantToRead(books.get(position))) {
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });


            }else if (parentActivity.equals("currentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //for confirmation before deleting//
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        builder.setMessage("Are u sure u want to delete" + books.get(position).getName() + "?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromCurrentlyReading(books.get(position))) {
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });

            }

        }else {
            holder.btnDelete.setVisibility(View.VISIBLE);

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //for confirmation before deleting//
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    builder.setMessage("Are u sure u want to delete" + books.get(position).getName() + "?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Utils.getInstance(mContext).removeFromFavorite(books.get(position))) {
                                Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged(); //call back interfaces
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create().show();

                }
            });

            TransitionManager.beginDelayedTransition(holder.parent);

            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;


        private  ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView txtAuthor, txtDescription;

        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);

            downArrow = itemView.findViewById(R.id.btnDownArr);
            upArrow = itemView.findViewById(R.id.btnUpArr);
            expandedRelLayout = itemView.findViewById(R.id.expandedRel);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtShortdesc);

            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());

                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());

                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
