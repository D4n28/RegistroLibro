package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class LibroCursorAdapter extends CursorAdapter {

    public LibroCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fila_libro, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitulo = view.findViewById(R.id.tvTitulo);
        TextView tvAutor = view.findViewById(R.id.tvAutor);

        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_NOMBRE));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_AUTOR));

        tvTitulo.setText(titulo);
        tvAutor.setText(autor);
    }
}
