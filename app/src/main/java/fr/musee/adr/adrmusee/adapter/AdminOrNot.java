package fr.musee.adr.adrmusee.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.musee.adr.adrmusee.AdminActivity;
import fr.musee.adr.adrmusee.CompteActivity;
import fr.musee.adr.adrmusee.R;

public class AdminOrNot extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String user_id;
    private String isadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        user_id=mAuth.getCurrentUser().getUid();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    isadmin=dataSnapshot.child("isadmin").getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(isadmin=="1"){
            startActivity(new Intent(AdminOrNot.this, AdminActivity.class));
            Toast.makeText(this, String.valueOf(isadmin), Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(AdminOrNot.this, CompteActivity.class));
            Toast.makeText(this, String.valueOf(isadmin), Toast.LENGTH_SHORT).show();
        }
    }
}
