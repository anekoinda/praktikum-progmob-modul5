package id.anekoinda.vaksini4;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    EditText InputNik, InputPassword;
    Button ButtonLogin;
    TextView buttonDaftar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InputNik = (EditText)findViewById(R.id.inputNik);
        InputPassword = (EditText)findViewById(R.id.inputPassword);
        ButtonLogin = (Button)findViewById(R.id.buttonLogin);

        dbHelper = new DBHelper(this);

        TextView buttonDaftar = (TextView)findViewById(R.id.buttonDaftar);

        buttonDaftar.setText(fromHtml("I don't have account yet. " +
                "</font><font color='#3b5998'>create one</font>"));
        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, daftar.class));
            }
        });

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = InputNik.getText().toString().trim();
                String password = InputPassword.getText().toString().trim();

                Boolean res = dbHelper.checkUser(nik,password);
                if(res == true){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
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

//public class login extends AppCompatActivity {

    Button buttonLogin;
    TextView buttonDaftar;
    EditText inputNik, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonDaftar = findViewById(R.id.buttonDaftar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), daftar.class);
                startActivity(intent);
            }
        });
    }
}