package com.example.ljhonnatan.bdremoto.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccesoDatos extends SQLiteOpenHelper {

    private static String nombreBd = "bdsqlitecoffee"; //Es el nombre de la base de datos
    private static int versionBd = 1; //Version de la Base de dstos
    public static Context aplicacion; //aplicacion a la que pertenece la bd


    private static String tablaUsuario =
            "create table usuario(dni varchar(8) primary key, nombre varchar(50), email varchar(40), clave varchar(32), estado varchar(1));";
    private static String tablaUsuarioDatos[] = {
            "insert into usuario values('23456789','Isis Paola', 'paola2@hotmail.com', md5('789'), 'A');",
    };

    public AccesoDatos() {
        super(aplicacion, nombreBd, null, versionBd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaUsuario);
        //db.execSQL(tabla);
        //db.execSQL(tablaDistrito);

        for (int i = 0 ; i < tablaUsuarioDatos.length; i++){
            db.execSQL(tablaUsuarioDatos[i]);
        }

        //for (int i = 0 ; i < tablaProvinciaDatos.length; i++){
        // db.execSQL(tablaProvinciaDatos[i]);
        //}

        //for (int i = 0 ; i < tablaDistritoDatos.length; i++){
        //  db.execSQL(tablaDistritoDatos[i]);
        //}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //puede ser un alter table como actualizar

    }
}
