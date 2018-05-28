package fr.musee.adr.adrmusee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText inputname;
    private EditText inputemail;
    private EditText inputphone;
    private EditText inputpassword;
    private Button ButtonSignup;


    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    private Long isadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputname = (EditText) findViewById(R.id.inputname);
        inputemail = (EditText) findViewById(R.id.inputemail);
        inputphone = (EditText) findViewById(R.id.inputphone);
        inputpassword = (EditText) findViewById(R.id.inputpassword);
        ButtonSignup = (Button) findViewById(R.id.buttonsignup);
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        isadmin = 0L;


        ButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startSignUp();
            }
        });

    }


    private void startSignUp(){
        final String name = inputname.getText().toString();
        final String email = inputemail.getText().toString();
        final String phone = inputphone.getText().toString();
        String password = inputpassword.getText().toString();
        final Long admin= isadmin;

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone)){
            mProgress.setMessage("Signing up ...");
            mProgress.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(user_id);
                        FirebaseDatabase.getInstance().getReference().child("Admin").child(user_id).child("isadmin").setValue(admin);
                        FirebaseDatabase.getInstance().getReference().child("Admin").child(user_id).child("name").setValue(name);
                        current_user_db.child("name").setValue(name);
                        current_user_db.child("phone").setValue(phone);
                        current_user_db.child("isadmin").setValue(admin);
                        current_user_db.child("email").setValue(email);
                        mProgress.dismiss();

                        Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    }else{
                        mProgress.dismiss();
                        Toast.makeText(SignUpActivity.this, "Ce mail existe déjà", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            Toast.makeText(SignUpActivity.this, "Plusieurs champs sont vides", Toast.LENGTH_LONG).show();
        }
    }
}
