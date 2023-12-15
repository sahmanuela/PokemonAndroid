package com.android.pokemon.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pokemon.R;

import org.w3c.dom.Text;


public class PerfilActivity  extends AppCompatActivity {
    private final static String PREFERENCES = "preferences"; //File's name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView storedValue = findViewById(R.id.storedValue);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        EditText registerName = findViewById(R.id.registerName);
        EditText registerEmail = findViewById(R.id.registerEmail);
        Button returnButtonSignUp = findViewById(R.id.returnButtonSignUp);

        // Button: Redirect to profile page
        returnButtonSignUp.setOnClickListener( (v -> {
            Log.d("PerfilActivity", "Botão Return clicado");

        }));

        // Button: Save Data
        buttonRegister.setOnClickListener( (v -> {
            Log.d("PerfilActivity", "Botão Register clicado");
            SharedPreferences preferences = getSharedPreferences(PREFERENCES, 0);
            SharedPreferences.Editor editor = preferences.edit();

            String nome = registerName.getText().toString().trim();
            String email = registerEmail.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill in your name and email!", Toast.LENGTH_SHORT).show();
            } else {
                editor.putString("nome", nome);
                editor.putString("email", email); // Adiciona o e-mail ao SharedPreferences
                editor.apply();
                storedValue.setText("Hello, " + nome + "\nEmail: " + email);
                Toast.makeText(getApplicationContext(), "Name and Email saved in SharedPreferences", Toast.LENGTH_SHORT).show();
            }

            // Recuperar valores
            SharedPreferences retrievedPreferences = getSharedPreferences(PREFERENCES, 0);
            String retrievedNome = retrievedPreferences.getString("nome", "User not defined!");
            String retrievedEmail = retrievedPreferences.getString("email", "Email not defined!");

            if (!retrievedNome.equals("User not defined!") || !retrievedEmail.equals("Email not defined!")) {
                storedValue.setText("Hello, " + retrievedNome + "\nEmail: " + retrievedEmail);
            } else {
                storedValue.setText("User or Email not defined!");
            }
        }));
    }

}