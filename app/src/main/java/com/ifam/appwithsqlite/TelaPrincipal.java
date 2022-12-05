package com.ifam.appwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity {
    Button atualizar, excluir, sair;

    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        atualizar = findViewById(R.id.p_atualizar);
        excluir = findViewById(R.id.p_excluir);
        sair = findViewById(R.id.p_sair);

        dao = new UsuarioDAO(this);

        acao();
    }

    public void acao(){
        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPrincipal.this, Atualizar.class);
                startActivity(intent);
                finish();
            }
        });
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.deletar(Sessao.usuario.getId());
                Sessao.usuario = null;
                Toast.makeText(TelaPrincipal.this, "Usuário excluído com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TelaPrincipal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}