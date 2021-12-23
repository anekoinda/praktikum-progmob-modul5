package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PilihRumahSakit extends AppCompatActivity {
    CardView rs;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    private ArrayList<ModelRs> dataholder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_rumah_sakit);
        DBHelper db = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_data_rs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursorRs = new DBHelper(this).readDataRsVaksin();

        while(cursorRs.moveToNext()){
            ModelRs obj = new ModelRs(cursorRs.getString(0), cursorRs.getString(1), cursorRs.getString(2), cursorRs.getString(3),cursorRs.getString(4));
            dataholder.add(obj);
        }

        AdapterPilihRs adapter = new AdapterPilihRs(dataholder, PilihRumahSakit.this, sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }
}