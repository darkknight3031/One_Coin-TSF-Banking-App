package com.example.onecoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    myadapter myadapter;
    RelativeLayout Card;
    DatabaseReference RefA;
    String xAmt;
    TextView Amt;
    ImageView Transection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Card=findViewById(R.id.card);
Amt=findViewById(R.id.balanceAmt);

Transection=findViewById(R.id.TranBtn);
Transection.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),Transections_Activity.class);
        startActivity(intent);
    }
});
        RefA=FirebaseDatabase.getInstance().getReference().child("Admin").child("1").child("balance");
        RefA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                xAmt=snapshot.getValue().toString();
                Amt.setText("₹"+xAmt);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        mRecyclerView =  findViewById(R.id.recyclerview2);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4,GridLayoutManager.VERTICAL,false));

        FirebaseRecyclerOptions<model> options=
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"),model.class)
                        .build();

        myadapter=new myadapter(options,getApplicationContext());
        mRecyclerView.setAdapter(myadapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

}