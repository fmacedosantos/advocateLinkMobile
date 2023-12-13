package com.example.loginvsf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginvsf.Cadastro;
import com.example.loginvsf.Menu;
import com.example.loginvsf.R;
import com.example.loginvsf.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private CheckBox checkBoxLembrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkBoxLembrar = findViewById(R.id.checkBoxLembrar);

        binding.btnLogin.setOnClickListener(view -> login());

        TextView tvCadastrar = findViewById(R.id.tvCadastrar);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para abrir a Activity Cadastrar
                Intent intent = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(intent);
            }
        });

        // Recupera as preferências salvas e preenche os campos se a opção "Lembrar senha" estiver marcada
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        checkBoxLembrar.setChecked(preferences.getBoolean("lembrarSenha", false));
        if (checkBoxLembrar.isChecked()) {
            binding.emailLogin.setText(preferences.getString("email", ""));
            binding.senhaLogin.setText(preferences.getString("senha", ""));
        }
    }

    private void login() {
        String email = binding.emailLogin.getText().toString();
        String senha = binding.senhaLogin.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String savedEmail = sharedPref.getString("Email", "");
        String savedSenha = sharedPref.getString("Senha", "");

        // Salva os dados se a opção "Lembrar senha" estiver marcada
        if (checkBoxLembrar.isChecked()) {
            SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
            editor.putString("email", email);
            editor.putString("senha", senha);
            editor.putBoolean("lembrarSenha", true);
            editor.apply();
        } else {
            // Se a opção não estiver marcada, limpa os dados salvos
            SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
            editor.remove("email");
            editor.remove("senha");
            editor.putBoolean("lembrarSenha", false);
            editor.apply();
        }

        if (email.equals(savedEmail) && senha.equals(savedSenha)) {
            // Se o email e a senha estiverem corretos, redirecione para a próxima tela
            startActivity(new Intent(this, Menu.class));
        } else {
            // Se o email ou a senha estiverem incorretos, exiba uma mensagem de erro
            Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
