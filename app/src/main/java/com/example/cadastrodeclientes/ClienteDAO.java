package com.example.cadastrodeclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void inserir(Cliente cliente, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.nome );
        valores.put("telefone", cliente.telefone );
        valores.put("celular", cliente.celular );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("cadCliente", null, valores);
    }

    public static void editar(Cliente cliente, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.nome );
        valores.put("telefone", cliente.telefone );
        valores.put("celular", cliente.celular );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("cadCliente", valores, " id = " + cliente.id , null );
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("cadCliente", " id = " + id , null);
    }

    public static List<Cliente> getClientes(Context context){
        List<Cliente> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, telefone, celular FROM cadCliente ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Cliente cliente = new Cliente();
                cliente.id = cursor.getInt( 0);
                cliente.nome = cursor.getString(1);
                cliente.telefone = cursor.getString(2);
                cliente.celular = cursor.getString(3);
                lista.add( cliente );
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static Cliente getClienteById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, telefone, celular FROM cadCliente WHERE id = " + id, null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Cliente cliente = new Cliente();
            cliente.id = cursor.getInt( 0);
            cliente.nome = cursor.getString(1);
            cliente.telefone = cursor.getString(2);
            cliente.celular = cursor.getString(3);
            return cliente;
        }else{
            return null;
        }
    }

}
