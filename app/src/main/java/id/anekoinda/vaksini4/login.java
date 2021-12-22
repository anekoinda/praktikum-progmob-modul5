package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.BufferUnderflowException;

public class login extends AppCompatActivity {

    Button buttonLogin,btnLogin;
    TextView buttonDaftar;
    EditText inputNik, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBHelper db = new DBHelper(this);

        inputNik = findViewById(R.id.inputNik);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin= findViewById(R.id.button_login);
        //buttonLogin = findViewById(R.id.button_register);
        buttonDaftar = findViewById(R.id.buttonDaftar);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String txt_nik  = inputNik.getText().toString();
               String txt_pass = inputPassword.getText().toString();

               if(txt_nik.equals("") || txt_pass.equals(""))
                   Toast.makeText(login.this, "Data tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
               else{
                    Boolean checknikpass = db.checknikpassword(txt_nik, txt_pass);
                    if(checknikpass == true){
                        Toast.makeText(login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                   }else{
                        Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
               }
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), daftar.class);
                startActivity(intent);
            }
        });
    }
}