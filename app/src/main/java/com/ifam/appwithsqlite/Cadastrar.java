package com.ifam.appwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastrar extends AppCompatActivity {
    TextView nome, email, senha;
    Button registrar, cancelar;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        nome = findViewById(R.id.cad_nome);
        email = findViewById(R.id.cad_email);
        senha = findViewById(R.id.cad_senha);

        registrar = findViewById(R.id.cad_registrar);
        cancelar = findViewById(R.id.cad_cancelar);

        dao = new UsuarioDAO(this);

        acao();
    }

    public void acao(){
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUsuario();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cadastrar.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setSenha(senha.getText().toString());

        Long id = dao.inserir(usuario);
        if(id != null){
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Cadastrar.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}