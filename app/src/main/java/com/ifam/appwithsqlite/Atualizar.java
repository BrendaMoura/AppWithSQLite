package com.ifam.appwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Atualizar extends AppCompatActivity {
    EditText nome, email, senha;
    Button atualizar, cancelar;

    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        nome = findViewById(R.id.at_nome);
        email = findViewById(R.id.at_email);
        senha = findViewById(R.id.at_senha);

        atualizar = findViewById(R.id.at_atualizar);
        cancelar = findViewById(R.id.at_cancelar);

        dao = new UsuarioDAO(this);

        getInfo();
        acao();
    }

    public void getInfo(){
        nome.setText(Sessao.usuario.getNome());
        email.setText(Sessao.usuario.getEmail());
        senha.setText(Sessao.usuario.getSenha());
    }

    public void acao(){
        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario(Sessao.usuario.getId(), nome.getText().toString(), email.getText().toString(), senha.getText().toString());
                dao.atualizar(usuario);
                Sessao.usuario = usuario;
                Toast.makeText(Atualizar.this, "Usuário atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Atualizar.this, TelaPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Atualizar.this, TelaPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}