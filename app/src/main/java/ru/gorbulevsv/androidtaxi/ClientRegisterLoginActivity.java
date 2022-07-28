package ru.gorbulevsv.androidtaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ClientRegisterLoginActivity extends AppCompatActivity {
    EditText clientEmail, clientPassword;
    Button buttonClientLogin, buttonClientRegister;
    FirebaseAuth firebaseAuth;
    ProgressDialog registerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register_login);

        firebaseAuth = FirebaseAuth.getInstance();
        registerDialog = new ProgressDialog(this);

        clientEmail = findViewById(R.id.clientEmail);
        clientPassword = findViewById(R.id.clientPassword);
        buttonClientLogin = findViewById(R.id.buttonClientLogin);
        buttonClientLogin.setOnClickListener(view -> {
            String email = clientEmail.getText().toString();
            String password = clientPassword.getText().toString();
            registerDialog.setTitle("Вход клиента");
            registerDialog.setMessage("Пожалуйста, дождитесь результата!");
            registerDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Вход прошёл успешно!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ошибка входа!", Toast.LENGTH_SHORT).show();
                }
                registerDialog.dismiss();
            });
        });
        buttonClientRegister = findViewById(R.id.buttonClientRegister);
        buttonClientRegister.setOnClickListener(view -> {
            String email = clientEmail.getText().toString();
            String password = clientPassword.getText().toString();
            registerDialog.setTitle("Регистрация клиента");
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