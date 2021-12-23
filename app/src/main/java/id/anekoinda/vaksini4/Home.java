package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Home extends AppCompatActivity {
    static int kondisi_kesehatan = 0;
    Button button_daftar;
    EditText nik, nama, telepon;
    CheckBox check_tidak, check_flu, check_hamil;
    RadioGroup radioGroup;
    RadioButton jenis_kelamin;
    SeekBar seekbar_kondisi;
    TextView persentase_kondisi;
    String keterangan;
    String is_valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DBHelper db = new DBHelper(this);

        keterangan = " ";
        is_valid = "0";
        nik = findViewById(R.id.input_nik);
        nama = findViewById(R.id.input_nama);
        telepon = findViewById(R.id.input_telepon);
        button_daftar = findViewById(R.id.button_daftar);
        radioGroup = findViewById(R.id.radio_jenkel);
        check_tidak = findViewById(R.id.check_tidak);
        check_flu = findViewById(R.id.check_flu);
        check_hamil = findViewById(R.id.check_hamil);
        seekbar_kondisi = findViewById(R.id.seekbar_kondisi);
        persentase_kondisi = findViewById(R.id.persentase);
        seekbar_kondisi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                persentase_kondisi.setText(progress + ("%"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        button_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_jenkel = radioGroup.getCheckedRadioButtonId();
                jenis_kelamin = findViewById(selected_jenkel);

                String kondisi_kesehatan = "";
                if(check_flu.isChecked() && check_hamil.isChecked()){
                    kondisi_kesehatan += "Flu, Hamil";
                }
                else{
                    if(check_tidak.isChecked()) {
                        kondisi_kesehatan += "Tidak ada keluhan";
                    }
                    if(check_flu.isChecked()) {
                        kondisi_kesehatan += "Flu";
                    }
                    if(check_hamil.isChecked()) {
                        kondisi_kesehatan += "Hamil";
                    }
                }

                String txt_nik =  nik.getText().toString();
                String txt_nama =  nama.getText().toString();
                String txt_telepon =  telepon.getText().toString();
                String txt_jenkel = jenis_kelamin.getText().toString();
                String txt_kondisi = kondisi_kesehatan;
                String txt_persentase = String.valueOf(seekbar_kondisi.getProgress());

                Boolean checkinsert = db.insert(txt_nik, txt_nama, txt_telepon, txt_jenkel, txt_kondisi, txt_persentase, " ", "0");
                if(checkinsert == true){
                    Intent intent = new Intent(getApplicationContext(), TiketPendaftaran.class);
                    Bundle data1 = new Bundle();
                    data1.putString("NIK", txt_nik);
                    intent.putExtras(data1);
                    startActivity(intent);
                }else{
                    Toast.makeText(Home.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
                }

                nik.setText(null);
                nama.setText(null);
                telepon.setText(null);
                radioGroup.clearCheck();
                check_flu.setChecked(false);
                check_hamil.setChecked(false);
                check_tidak.setChecked(false);
                seekbar_kondisi.setProgress(0);
            }
        });
    }
}