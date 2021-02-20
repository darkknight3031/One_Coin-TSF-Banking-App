package com.example.onecoin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myViewholder> {

    Context context;

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options, Context applicationContext) {
        super(options);

        this.context=applicationContext;

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, model model) {


        holder.NaeText.setText(model.getName());


         Glide.with(holder.Image.getContext()).load(model.getImageURL()).into(holder.Image);

        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Transfer_Activity.class);
                Bundle b=new Bundle();
                b.putString("Img",model.getImageURL());

                b.putString("name",model.getName());
                b.putString("bal",model.getBalance());
                b.putString("email",model.getEmail());
                b.putString("acno",model.getAcno());
                b.putString("uid",model.getUid());

                intent.putExtras(b);
                v.getContext().startActivity(intent);
               // Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view2,parent,false);
       return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

    TextView NaeText;
    ImageView Image;

    public myViewholder(@NonNull View itemView) {
        super(itemView);

        NaeText=(TextView)itemView.findViewById(R.id.NameTxt);
        Image=(ImageView)itemView.findViewById(R.id.people_img);


    }
}


}
