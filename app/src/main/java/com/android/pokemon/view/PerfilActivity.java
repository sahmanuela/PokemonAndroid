package com.android.pokemon.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pokemon.R;

import org.w3c.dom.Text;


public class PerfilActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView storedValue = findViewById(R.id.storedValue);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        EditText registerName = findViewById(R.id.registerName);
        EditText registerEmail = findViewById(R.id.registerEmail);
        Button returnButtonSignUp = findViewById(R.id.returnButtonSignUp);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        SharedPreferences preferences = getSharedPreferences(Constantes.PREFERENCES, 0);
        SharedPreferences.Editor editor = preferences.edit();
        String retrievedNome = preferences.getString("nome", "");
        String retrievedEmail = preferences.getString("email", "");

        if (!retrievedNome.isEmpty() && !retrievedEmail.isEmpty()) {
            registerName.setText(retrievedNome);
            registerEmail.setText(retrievedEmail);
            buttonRegister.setText("Update");
            storedValue.setText("Hello, " + retrievedNome + "\nEmail: " + retrievedEmail);
        } else {
            buttonDelete.setEnabled(false);
        }
        // Button: Redirect to profile page
        returnButtonSignUp.setOnClickListener( (v -> {
            Log.d("PerfilActivity", "Botão Return clicado");
            Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }));

        // Button: Save Data
        buttonRegister.setOnClickListener( (v -> {
            InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            Log.d("PerfilActivity", "Botão Register clicado");
            String nome = registerName.getText().toString().trim();
            String email = registerEmail.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty()) {
                storedValue.setText("Fill in your name and email!");
            } else {
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.apply();
                storedValue.setText("Dados atualizados!");
            }
        }));

        // Button: Delete
        buttonDelete.setOnClickListener( (v -> {
            editor.putString("nome", null);
            editor.putString("email", null);
            editor.apply();
            storedValue.setText("Usuário excluído!");
        }));
    }
}
