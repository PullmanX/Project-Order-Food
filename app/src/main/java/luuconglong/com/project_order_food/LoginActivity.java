package luuconglong.com.project_order_food;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_REGISTER = 1;
    EditText txtUsername, txtPassword;
    Button btnRegister, btnLogin;

    String username = "", password = "", fullname = "";

    @SuppressLint({"WrongViewCast", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mapping fields
        txtUsername = findViewById(R.id.al_txt_username);
        txtPassword = findViewById(R.id.al_txt_password);
        btnRegister = findViewById(R.id.al_btn_register);
        btnLogin = findViewById(R.id.al_btn_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, "Check login", Toast.LENGTH_SHORT).show();
                String uname = txtUsername.getText().toString();
                String pwd = txtPassword.getText().toString();
                if(username.equals(username) && pwd.equals(password)){
                    Toast.makeText(LoginActivity.this, "Wellcome " + fullname, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQUEST_CODE_REGISTER:
                //Du lieu duoc tra ve tu Register
                if(data != null){
                    fullname = data.getStringExtra("fullname");
                    username = data.getStringExtra("username");
                    password = data.getStringExtra("pwd");
                }
//                Toast.makeText(LoginActivity.this, "Check login", Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, fullname, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}