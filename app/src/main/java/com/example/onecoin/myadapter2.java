package com.example.onecoin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;


public class myadapter2 extends FirebaseRecyclerAdapter<model2, myadapter2.myViewholder> {

    Context context;

    public myadapter2(@NonNull FirebaseRecyclerOptions<model2> options, Context applicationContext) {
        super(options);

        this.context=applicationContext;

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, model2 model) {


        holder.NaeText.setText("Harsh → "+model.getName());
        holder.Amount.setText("₹ "+model.getAmount());
        holder.Date.setText(model.getDate());
        holder.Stat.setText(model.getTime());
        if (model.getTime().equals("Success")) {

            holder.Stat.setTextColor(Color.parseColor("#008000"));
        }
        else {
            holder.Stat.setTextColor(Color.parseColor("#FF0000"));

        }
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
       return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

    TextView NaeText,Amount,Date,Stat;
    ImageView Image;

    public myViewholder(@NonNull View itemView) {
        super(itemView);

        NaeText=(TextView)itemView.findViewById(R.id.NameTxt);
       Amount=(TextView)itemView.findViewById(R.id.Amt);
       Date=(TextView)itemView.findViewById(R.id.timd);
        Stat=(TextView)itemView.findViewById(R.id.status);
    }
}


}
