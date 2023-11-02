package com.example.loginvsf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginvsf.databinding.ActivityLoginBinding;

public class LoginAdvogado extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> login());

        TextView tvCadastrar = findViewById(R.id.tvCadastrar);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para abrir um site na web
                String url = "http://www.example.com"; // Substitua pela URL que você quer abrir
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
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
            startActivity(new Intent(this, Menu.class));
        } else {
            // Se o email ou a senha estiverem incorretos, exiba uma mensagem de erro
            Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
