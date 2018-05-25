package fr.musee.adr.adrmusee;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AdminOrNot {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String isAdmin;

    String user_id = mAuth.getCurrentUser().getUid();
    String user = mAuth.getCurrentUser().getEmail();
    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                isAdmin=dataSnapshot.child("isadmin").getValue().toString();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}
