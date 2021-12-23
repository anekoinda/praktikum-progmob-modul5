package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private EditText edNama, edNik, edTelepon;
    private Button btnVaksin;

    String nikUser;
    Integer Id;
    String Nik, Nama, Telepon;
    String updateNik, updateNama, updateTelepon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edNama = findViewById(R.id.inputNama);
        edNik = findViewById(R.id.inputNik);
        edTelepon = findViewById(R.id.inputTelepon);
        btnVaksin = findViewById(R.id.buttonVaksin);

        Intent i = getIntent();

        nikUser = i.getExtras().getString("NIK");
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getUser(nikUser);
        while (cursor.moveToNext()){
            Id = cursor.getInt(0);
            Nik = cursor.getString(1);
            Nama = cursor.getString(2);
            Telepon = cursor.getString(3);
        }
        edNik.setText(Nik);
        edNama.setText(Nama);
        edTelepon.setText(Telepon);


        btnVaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(Profile.this);
                updateNik = edNik.getText().toString();
                updateNama = edNama.getText().toString();
                updateTelepon = edTelepon.getText().toString();
                db.updateUser(Id, updateNik, updateNama, updateTelepon);
            }
        });

    }

}