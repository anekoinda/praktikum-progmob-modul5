package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static java.sql.Types.NULL;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonVaksin;
    SQLiteDatabase sqLiteDatabase;
    TextView covidPositif, covidNegatif, covidMeninggal;
    RecyclerView recyclerView;
    private ArrayList<ModelRs> dataholder = new ArrayList<>();
    String nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_data_rs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent i = getIntent();
        nik = (i.getExtras().getString("NIK"));

        Cursor cursorRs = new DBHelper(this).readDataRs();

        while(cursorRs.moveToNext()){
            ModelRs obj = new ModelRs(cursorRs.getString(0), cursorRs.getString(1), cursorRs.getString(2), cursorRs.getString(3), cursorRs.getString(4));
            dataholder.add(obj);
        }

        AdapterRS adapter = new AdapterRS(dataholder, MainActivity.this, sqLiteDatabase);
        recyclerView.setAdapter(adapter);

        covidPositif = findViewById(R.id.covidPositif);
        covidNegatif = findViewById(R.id.covidNegatif);
        covidMeninggal = findViewById(R.id.covidMeninggal);
        Cursor cursor = new DBHelper(this).readDataCovid();

        if(cursor.moveToFirst()){
            int positif = cursor.getColumnIndex("positif");
            int negatif = cursor.getColumnIndex("negatif");
            int meninggal = cursor.getColumnIndex("meninggal");

            covidNegatif.setText(cursor.getString(negatif));
            covidPositif.setText(cursor.getString(positif));
            covidMeninggal.setText(cursor.getString(meninggal));
        }

        cursor.close();
        db.close();

        buttonVaksin = findViewById(R.id.buttonVaksin);
        buttonVaksin.bringToFront();
        buttonVaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PilihRumahSakit.class);
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
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            intent.putExtra("NIK", nik);
            startActivity(intent);
//        } else if (item.getItemId() == R.id.tiket) {
//            startActivity(new Intent(this, TiketPendaftaran.class));
        } else if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, login.class));
        }
        return true;
    }
}