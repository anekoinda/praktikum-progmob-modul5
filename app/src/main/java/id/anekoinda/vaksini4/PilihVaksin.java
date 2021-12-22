package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

public class PilihVaksin extends AppCompatActivity {

    CardView vaksin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_vaksin);

        vaksin = findViewById(R.id.vaksin);
        vaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PilihRumahSakit.class);
                startActivity(intent);
            }
        });
    }
}