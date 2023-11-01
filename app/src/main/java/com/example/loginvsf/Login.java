package com.example.loginvsf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginvsf.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> login());
    }

    private void login() {
        String email = binding.emailLogin.getText().toString();
        String senha = binding.senhaLogin.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String savedEmail = sharedPref.getString("Email", "");
        String savedSenha = sharedPref.getString("Senha", "");

        if (email.equals(savedEmail) && senha.equals(savedSenha)) {
            // Se o email e a senha estiverem corretos, redirecione para a próxima tela
            startActivity(new Intent(this, Redirect.class));
        } else {
            // Se o email ou a senha estiverem incorretos, exiba uma mensagem de erro
            Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
