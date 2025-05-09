package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LibroController {
    private BaseDatos dbHelper;

    public LibroController(Context context) {
        dbHelper = new BaseDatos(context);
    }

    public long insertar(Libro libro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DefBD.LibroEntry.COLUMN_CODIGO, libro.getCodigo());
        values.put(DefBD.LibroEntry.COLUMN_NOMBRE, libro.getNombre());
        values.put(DefBD.LibroEntry.COLUMN_AUTOR, libro.getAutor());
        values.put(DefBD.LibroEntry.COLUMN_EDITORIAL, libro.getEditorial());
        return db.insert(DefBD.LibroEntry.TABLE_NAME, null, values);
    }

    public int eliminar(String codigo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(DefBD.LibroEntry.TABLE_NAME, DefBD.LibroEntry.COLUMN_CODIGO + "=?", new String[]{codigo});
    }

    public Cursor obtenerTodos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(
                DefBD.LibroEntry.TABLE_NAME,
                null, null, null, null, null, null
        );
    }
}