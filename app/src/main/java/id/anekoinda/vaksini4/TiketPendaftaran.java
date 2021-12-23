package id.anekoinda.vaksini4;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TiketPendaftaran extends AppCompatActivity {
    private TextView txtNik, txtNama, txtTelepon, txtJenkel, txtKondisi, txtPersentasi;
    ImageView qr_output;
    Button button_qr;
    int Id;
    String Tiket;
    String nik, nama, telepon, jenis_kelamin, kondisi_kesehatan, persentasi_kondisi;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_pendaftaran);

        qr_output = findViewById(R.id.qr_output);
        button_qr = findViewById(R.id.button_qr);

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
        while (cursor.moveToNext()) {
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

        button_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeWriter writer = new QRCodeWriter();
                String tiket_nik = nik.trim();
                if (TextUtils.isEmpty(txtNik.getText().toString())) {
                    Toast.makeText(TiketPendaftaran.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
                } else {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimen = width < height ? width : height;
                    dimen = dimen * 3 / 4;
                    qrgEncoder = new QRGEncoder(txtNik.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qr_output.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        Log.e("Tag", e.toString());
                    }
                }
            }
        });
    }
}