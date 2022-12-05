package com.ifam.appwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView registrar;
    EditText login, senha;
    Button entrar;

    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        senha = findViewById(R.id.senha);
        registrar = findViewById(R.id.ir_registro);

        entrar = findViewById(R.id.entrar);

        dao = new UsuarioDAO(this);

        acao();
    }

    public void acao(){
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = dao.getUser(login.getText().toString(), senha.getText().toString());
                if(usuario != null){
                    Sessao.usuario = usuario;
                    Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(intent);
                finish();
            }
        });
    }
}