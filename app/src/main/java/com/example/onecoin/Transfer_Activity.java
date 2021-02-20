package com.example.onecoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Transfer_Activity extends AppCompatActivity {

    ImageView Back;
    CardView TransBtn;
    EditText AmtEdt;
    ImageView TImge;
    TextView TName,Temail,TCard,TBal;
    DatabaseReference Ref,RefA,RefT;
    String x,MyAmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);


        String email = getIntent().getExtras().getString("email");
        String Namez = getIntent().getExtras().getString("name");
        String ProUrl = getIntent().getExtras().getString("Img");
       String Bal = getIntent().getExtras().getString("bal");
        String acno = getIntent().getExtras().getString("acno");
        String uid = getIntent().getExtras().getString("uid");

        Double Bal2 = Double.valueOf(Bal);
        Bal= String.valueOf(Bal2);
        TImge=findViewById(R.id.tImg);
        Glide.with(getApplicationContext()).load(ProUrl).into(TImge);
        TName=findViewById(R.id.tname);
        TName.setText(Namez);
        Temail=findViewById(R.id.temail);
        Temail.setText(email);
        TCard=findViewById(R.id.CardTxt);
        TCard.setText("XXXX XXXX "+acno);
        TBal=findViewById(R.id.BalText);
        TBal.setText("₹"+Bal);
AmtEdt=findViewById(R.id.EdtBal);

        Ref=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                 x=snapshot.child("balance").getValue().toString();
                TBal.setText("₹"+x);
                //Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        Back=findViewById(R.id.Backb);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        RefT=FirebaseDatabase.getInstance().getReference().child("Transaction");


        RefA=FirebaseDatabase.getInstance().getReference().child("Admin").child("1");
        RefA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                MyAmt=snapshot.child("balance").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        TransBtn=findViewById(R.id.TransferBtn);
        TransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (AmtEdt.getText().toString().isEmpty()){
                   Toast.makeText(Transfer_Activity.this, "Enter Amount!", Toast.LENGTH_SHORT).show();

               }
               else {

                   AmtEdt.getText().toString();
                   Double Xfinal=Double.valueOf(AmtEdt.getText().toString())+Double.valueOf(x);
                   String XFAmt= String.valueOf(Xfinal);
                   HashMap<String, Object> map = new HashMap<>();
                   map.put("balance", XFAmt);
                   Ref.updateChildren(map);

                   Double Mcr= Double.valueOf(MyAmt)-Double.valueOf(AmtEdt.getText().toString());
                   String MyFinal= String.valueOf(Mcr);
                   HashMap<String, Object> map2 = new HashMap<>();
                   map2.put("balance", MyFinal);
                   RefA.updateChildren(map2);

                   String date = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss", Locale.getDefault()).format(new Date());

                   HashMap<String, Object> map3 = new HashMap<>();
                   map3.put("amount", AmtEdt.getText().toString());
                   map3.put("name", Namez);
                   map3.put("date", date);
                   if (AmtEdt.getText().toString().equals("00")||AmtEdt.getText().toString().equals("0")){
                   map3.put("time", "Failed");
                       Toast.makeText(Transfer_Activity.this, "Transaction Failed!", Toast.LENGTH_SHORT).show();

                   }else {
                       map3.put("time", "Success");
                       Toast.makeText(Transfer_Activity.this, "Transaction Successful!", Toast.LENGTH_SHORT).show();

                   }
                    RefT.push().updateChildren(map3);


                   AmtEdt.setText(null);
               }
            }
        });
    }
}