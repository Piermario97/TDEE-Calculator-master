package com.peretti.tdeecalculator;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText editTextMail, editTextPass;
    private TextView textViewsignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        textViewsignin = (TextView) findViewById(R.id.textViewsignin);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        btnRegister.setOnClickListener(this);
        textViewsignin.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextMail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            // stop the function
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            // stop the function
            return;
        }
        progressDialog.setMessage("Registering user...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registrato con successo!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Errore, riprova!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view){
        if (view == btnRegister){
            registerUser();
        }
        if (view == textViewsignin){
            // open login activity
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
