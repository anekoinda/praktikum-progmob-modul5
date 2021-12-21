package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText InputNama, InputNik, InputTelepon, InputPassword;
    Button ButtonLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        dbHelper = new DBHelper(this);

        InputNama = (EditText)findViewById(R.id.inputNama);
        InputNik = (EditText)findViewById(R.id.inputNik);
        InputTelepon = (EditText)findViewById(R.id.inputTelepon);
        InputPassword = (EditText)findViewById(R.id.inputPassword);
        ButtonLogin = (Button)findViewById(R.id.buttonLogin);


        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = InputNama.getText().toString().trim();
                String nik = InputNik.getText().toString().trim();
                String telepon = InputTelepon.getText().toString().trim();
                String password = InputPassword.getText().toString().trim();
                ContentValues values = new ContentValues();


                 if (password.equals("") || nama.equals("")){
                    Toast.makeText(RegisterActivity.this, "Username or Password cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(DBHelper.row_nik, Niklogin);
                    values.put(DBHelper.row_password, Password);
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
