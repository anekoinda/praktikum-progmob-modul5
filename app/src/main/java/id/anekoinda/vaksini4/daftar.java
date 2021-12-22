package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class daftar extends AppCompatActivity {
    Button button_register;
    TextView button_login;
    EditText nama, nik, telepon, password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        DBHelper db = new DBHelper(this);

        nama = findViewById(R.id.inputNama);
        nik = findViewById(R.id.inputNik);
        telepon = findViewById(R.id.inputTelepon);
        password= findViewById(R.id.inputPassword);
        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_nik = nik.getText().toString();
                String txt_nama = nama.getText().toString();
                String txt_telepon = telepon.getText().toString();
                String txt_password = password.getText().toString();

                if (txt_nik.equals("") || txt_nama.equals("") || txt_telepon.equals("") || txt_password.equals(""))
                    Toast.makeText(daftar.this, "Data tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkinsert = db.insert(txt_nik, txt_nama, txt_telepon, txt_password);
                    if (checkinsert == true) {
                        Toast.makeText(daftar.this, "Register berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(daftar.this, "Register gagal", Toast.LENGTH_SHORT).show();
                    }

                    nik.setText(null);
                    nama.setText(null);
                    telepon.setText(null);
                    password.setText(null);
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

    }
}