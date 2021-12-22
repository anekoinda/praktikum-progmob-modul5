package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class PilihRumahSakit extends AppCompatActivity {

    private TextView namaVaksin, namaLokasi, jalan;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    private RecyclerView rvRS;
    private AdapterRS adapterRS;
    private ArrayList<RS> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_rumah_sakit);

        dbHelper = new DBHelper(PilihRumahSakit.this);
        adapterRS = new AdapterRS(data, PilihRumahSakit.this);

        namaVaksin = findViewById(R.id.nama_vaksin);
        namaLokasi = findViewById(R.id.nama_lokasi);
        jalan = findViewById(R.id.jalan);

//        loadData();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadData();
//    }
//
//    private void loadData(){
//        //reset data
//        data.clear();
//
//        int baris = Integer.parseInt(dbHelper.cari("select count(*) from rs where vaksin = '';"));
//        int kolom = 7;
//
//        String temp[][] = dbHelper.cari_array("select * from tb_buku", baris, kolom);
//        for (int i = 0; i < temp.length; i++){
//            Buku b = new Buku();
//            b.setId(temp[i][0]);
//            b.setJudul(temp[i][1]);
//            b.setNama(temp[i][2]);
//            b.setKategori(temp[i][3]);
//            b.setGenre(temp[i][4]);
//            b.setRating(temp[i][5]);
//            b.setVerif(temp[i][6]);
//            data.add(b);
//            adapterBuku.notifyDataSetChanged();
//        }
//    }

}