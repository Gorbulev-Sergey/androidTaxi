package ru.gorbulevsv.androidtaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DriverRegisterLoginActivity extends AppCompatActivity {
    EditText driverEmail, driverPassword;
    Button buttonDriverLogin, buttonDriverRegister;
    FirebaseAuth firebaseAuth;
    ProgressDialog registerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register_login);

        firebaseAuth = FirebaseAuth.getInstance();
        registerDialog = new ProgressDialog(this);

        driverEmail = findViewById(R.id.driverEmail);
        driverPassword = findViewById(R.id.driverPassword);
        buttonDriverLogin = findViewById(R.id.buttonDriverLogin);
        buttonDriverLogin.setOnClickListener(view -> {
            String email = driverEmail.getText().toString();
            String password = driverPassword.getText().toString();
            registerDialog.setTitle("Вход водителя");
            registerDialog.setMessage("Пожалуйста, дождитесь результата!");
            registerDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Вход прошёл успешно!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,DriverMapsActivity.class));
                } else {
                    Toast.makeText(this, "Ошибка входа!", Toast.LENGTH_SHORT).show();
                }
                registerDialog.dismiss();
            });
        });
        buttonDriverRegister = findViewById(R.id.buttonDriverRegister);
        buttonDriverRegister.setOnClickListener(view -> {
            String email = driverEmail.getText().toString();
            String password = driverPassword.getText().toString();
            registerDialog.setTitle("Регистрация водителя");
            registerDialog.setMessage("Пожалуйста, дождитесь результата!");
            registerDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ошибка регистрации!", Toast.LENGTH_SHORT).show();
                }
                registerDialog.dismiss();
            });
        });
    }
}