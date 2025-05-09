package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listaLibros;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLibros = findViewById(R.id.listaLibros);
        btnAgregar = findViewById(R.id.btnAgregar);

        cargarLibros();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EdicionActivity.class);
                startActivity(intent);
            }
        });

        listaLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listaLibros.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, EdicionActivity.class);
                intent.putExtra("codigo", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_CODIGO)));
                intent.putExtra("nombre", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_NOMBRE)));
                intent.putExtra("autor", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_AUTOR)));
                intent.putExtra("editorial", cursor.getString(cursor.getColumnIndexOrThrow(DefBD.LibroEntry.COLUMN_EDITORIAL)));
                startActivity(intent);
            }
        });
    }

    private void cargarLibros() {
        LibroController controller = new LibroController(this);
        Cursor cursor = controller.obtenerTodos();
        LibroCursorAdapter adapter = new LibroCursorAdapter(this, cursor);
        listaLibros.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLibros();
    }
}

