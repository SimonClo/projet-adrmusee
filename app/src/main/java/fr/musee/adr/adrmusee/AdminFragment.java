package fr.musee.adr.adrmusee;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminFragment extends Fragment {
    private EditText modifname;
    private EditText modifemail;
    private EditText modifphone;
    private EditText modifpassword;
    private Button Sedeconnecter;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String user_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_admin_fragment, null);
        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();

        modifname = (EditText) view.findViewById(R.id.modif_nom);
        modifemail = (EditText) view.findViewById(R.id.modif_email);
        modifphone = (EditText) view.findViewById(R.id.modif_phone);
        modifpassword = (EditText) view.findViewById(R.id.modif_mdp);
        Sedeconnecter = (Button) view.findViewById(R.id.Sedeconnecter);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        modifemail.setText(mAuth.getCurrentUser().getEmail());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("name").getValue().toString();
                    String phone=dataSnapshot.child("phone").getValue().toString();

                    modifname.setText(name);
                    modifphone.setText(phone);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Sedeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        return view;

    }

}

