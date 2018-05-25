package fr.musee.adr.adrmusee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Seconnecter;
    private Button Signup;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Signup = (Button) findViewById(R.id.sign_up);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.MotDePasse);
        Seconnecter = (Button) findViewById(R.id.SeConnecter);


        mAuth = FirebaseAuth.getInstance();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startView();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(MainActivity.this, CompteActivity.class));

                }
            }
        };

        Seconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });

    }
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void startView(){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
    public void startSignIn() {
        String email = Email.getText().toString();
        String motdepasse = Password.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(motdepasse)) {
            Toast.makeText(MainActivity.this, "Champs utilisateur ou mot de passe vide",Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,motdepasse).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Impossible de se connecter",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
