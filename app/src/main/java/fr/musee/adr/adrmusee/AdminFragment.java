package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminFragment extends Fragment {
    private EditText modifname;
    private EditText modifemail;
    private EditText modifphone;
    private EditText modifpassword;
    private Button Sedeconnecter;
    private DatabaseReference mDatabase;
    private ArrayList list_users;
    private ArrayList list_emails;
    private Button AdminButton;
    public static Basket userbasket;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_admin_fragment, null);
        userbasket = new Basket();
        list_users = new ArrayList<>();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String user_id=ds.getKey().toString();
                    String email=ds.child(user_id).child("email").getValue().toString();
                    list_users.add(user_id);
                    list_emails.add(email);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        AdminButton = (Button) view.findViewById(R.id.ButtonAdmin);
        final Spinner SpinnerUsers = (Spinner) view.findViewById(R.id.SpinnerUsers);
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,list_emails);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerUsers.setAdapter(dataAdapterR);

        SpinnerUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Email = String.valueOf(SpinnerUsers.getSelectedItem());
                Integer positionItem = (Integer) SpinnerUsers.getSelectedItemPosition();
                final String user_id = list_users.get(positionItem).toString();
                AdminButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.child(user_id).child("isadmin").setValue(1L);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;

    }

}

