package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class TiketPendaftaran extends AppCompatActivity {
    ImageView qr_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_pendaftaran);

        qr_output = findViewById(R.id.qr_output);
    }
}