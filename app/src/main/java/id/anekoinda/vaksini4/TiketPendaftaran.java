package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class TiketPendaftaran extends AppCompatActivity {
    //private static final String TAG = "ceknik";
    private TextView txtNik, txtNama, txtTelepon, txtJenkel, txtKondisi, txtPersentasi;
    ImageView qr_output;
    Button buttonBack;
    int Id;
    String Tiket;
    String nik, nama, telepon, jenis_kelamin, kondisi_kesehatan, persentasi_kondisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_pendaftaran);

        //Bundle extras = getIntent().getExtras();
        //String nik = extras.getString("NIK");
        //String pendaftaran = nik;
        //pendaftaran.trim();
        //Log.i(TAG, "nik" + pendaftaran);

        qr_output = findViewById(R.id.qr_output);
        buttonBack = findViewById(R.id.buttonBack);

        txtNik = findViewById(R.id.txt_nik);
        txtNama = findViewById(R.id.txt_nama);
        txtTelepon = findViewById(R.id.txt_telepon);
        txtJenkel = findViewById(R.id.txt_jenkel);
        txtKondisi = findViewById(R.id.txt_kondisi);
        txtPersentasi = findViewById(R.id.txt_persentase);

        Intent i = getIntent();

        Tiket = i.getExtras().getString("NIK");
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getTiket(Tiket);
        while (cursor.moveToNext()){
            Id = cursor.getInt(0);
            nik = cursor.getString(1);
            nama = cursor.getString(2);
            telepon = cursor.getString(3);
            jenis_kelamin = cursor.getString(4);
            kondisi_kesehatan = cursor.getString(5);
            persentasi_kondisi = cursor.getString(6);
        }
        txtNik.setText(nik);
        txtNama.setText(nama);
        txtTelepon.setText(telepon);
        txtJenkel.setText(jenis_kelamin);
        txtKondisi.setText(kondisi_kesehatan);
        txtPersentasi.setText(persentasi_kondisi);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

               // QRCodeWriter writer = new QRCodeWriter();
                //try {
                  // BitMatrix bitMatrix = writer.encode(pendaftaran, BarcodeFormat.QR_CODE, 512, 512);
                  // int width = bitMatrix.getWidth();
                  // int height = bitMatrix.getHeight();
                  // Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                  // for (int x = 0; x < width; x++) {
                      // for (int y = 0; y < height; y++) {
                           // bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        //}
                   // }
                    //((ImageView) findViewById(R.id.qr_output)).setImageBitmap(bmp);
                   //} catch (WriterException e) {
                    //e.printStackTrace();
               //}
            }
        });
    }
}