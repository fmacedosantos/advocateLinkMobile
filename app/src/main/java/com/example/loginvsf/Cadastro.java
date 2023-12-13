package com.example.loginvsf;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.loginvsf.databinding.ActivityCadastroBinding;

import java.io.FileOutputStream;
import java.io.IOException;

public class Cadastro extends AppCompatActivity {
    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCadastrar.setOnClickListener(view -> cadastrarDados());
    }

    private void cadastrarDados() {
        String usuarioCadastro = binding.usuarioCadastro.getText().toString();
        String emailCadastro = binding.emailCadastro.getText().toString();
        String senhaCadastro = binding.senhaCadastro.getText().toString();
        String confirmarSenhaCadastro = binding.confirmarSCadastro.getText().toString();

        // Adiciona a validação da senha
        if (senhaCadastro.length() > 5) {
            // Adiciona a validação da máscara de e-mail
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailCadastro).matches()) {
                if (senhaCadastro.equals(confirmarSenhaCadastro)) {
                    // Salva os dados no SharedPreferences se as senhas coincidirem
                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Usuario", usuarioCadastro);
                    editor.putString("Email", emailCadastro);
                    editor.putString("Senha", senhaCadastro);
                    editor.apply();

                    startActivity(new Intent(this, Login.class));
                } else {
                    Toast.makeText(this, "Senhas divergentes", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "A senha deve conter pelo menos 6 caracteres", Toast.LENGTH_SHORT).show();
        }
    }

}
