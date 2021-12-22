package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {
    static int kondisi_kesehatan=0;
    Button buttonVaksin;
    SQLiteDatabase sqLiteDatabase;
    EditText nik, nama, telepon;
    CheckBox check_tidak, check_flu, check_hamil;
    RadioGroup radioGroup;
    RadioButton jenis_kelamin;
    SeekBar seekbar_kondisi;
    TextView persentase_kondisi;
    String keterangan;
    String is_valid;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.execSQL("INSERT INTO tb_data_vaksin VALUES('22.500','900','899')");
        cursor = db.rawQuery("SELECT*FROM tb_data_vaksin", null);
        cursor.moveToFirst();
//        for (int i = 0; i<cursor.getCount(); i++){
//            cursor.getString(1), cursor.getString(1), cursor.getString(1);
//        }
        buttonVaksin = findViewById(R.id.buttonVaksin);
        buttonVaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PilihVaksin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil){
            startActivity(new Intent(this, Profile.class));
        } else if (item.getItemId() == R.id.tiket) {
            startActivity(new Intent(this, TiketPendaftaran.class));
        } else if (item.getItemId() == R.id.logout) {
            //startActivity(new Intent(this, SettingActivity.class));
        }
        return true;
    }
}