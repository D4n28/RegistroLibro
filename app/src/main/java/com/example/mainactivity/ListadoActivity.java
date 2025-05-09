package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ListadoActivity extends AppCompatActivity {
    private LibroController controller;
    private ListView lvLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lvLibros = findViewById(R.id.lvLibros);
        controller = new LibroController(this);

        cargarLista();

        lvLibros.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Cursor cursor = (Cursor) lvLibros.getItemAtPosition(position);
            Intent intent = new Intent(this, EdicionActivity.class);
            intent.putExtra("codigo", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_CODIGO)));
            intent.putExtra("nombre", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_NOMBRE)));
            intent.putExtra("autor", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_AUTOR)));
            intent.putExtra("editorial", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_EDITORIAL)));
            startActivity(intent);
        });
    }

    private void cargarLista() {
        Cursor cursor = controller.obtenerTodos();
        LibroCursorAdapter adapter = new LibroCursorAdapter(this, cursor);
        lvLibros.setAdapter(adapter);
    }
}

