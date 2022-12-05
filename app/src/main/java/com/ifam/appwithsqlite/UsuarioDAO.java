package com.ifam.appwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());

        return banco.insert("usuario", null, values);
    }

    public List<Usuario> obterTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = banco.query("usuario", new String[]{"id", "nome", "email", "senha"},null, null, null, null, null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getLong(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public Usuario getUser(String email, String senha){
        String[] args = { email, senha };
        Cursor cursor = banco.query("usuario", new String[]{"id", "nome", "email", "senha"}, "email=? AND senha=?", args, null, null, null);

        if(cursor.moveToFirst()){
            return new Usuario(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        return null;
    }

    public int deletar(Long id){
        return banco.delete("usuario", "id=?", new String[]{id.toString()});
    }

    public void atualizar(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());

        banco.update("usuario", values, "id=?", new String[]{usuario.getId().toString()});
    }
}
