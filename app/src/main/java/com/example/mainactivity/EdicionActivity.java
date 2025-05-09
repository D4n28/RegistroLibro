package com.example.mainactivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EdicionActivity extends AppCompatActivity {
    private EditText etCodigo, etNombre, etAutor, etEditorial;
    private BaseDatos dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);

        etCodigo = findViewById(R.id.etIdLibro);
        etNombre = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etAutor);
        etEditorial = findViewById(R.id.etEditorial);
        dbHelper = new BaseDatos(this);

        // Si recibimos datos, es edición
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            etCodigo.setText(extras.getString("codigo"));
            etNombre.setText(extras.getString("nombre"));
            etAutor.setText(extras.getString("autor"));
            etEditorial.setText(extras.getString("editorial"));
        }
    }

    public void guardarLibro(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DefBD.LibroEntry.COLUMN_CODIGO, etCodigo.getText().toString());
        values.put(DefBD.LibroEntry.COLUMN_NOMBRE, etNombre.getText().toString());
        values.put(DefBD.LibroEntry.COLUMN_AUTOR, etAutor.getText().toString());
        values.put(DefBD.LibroEntry.COLUMN_EDITORIAL, etEditorial.getText().toString());

        String codigo = etCodigo.getText().toString();
        // Verificar si ya existe
        Cursor cursor = db.query(DefBD.LibroEntry.TABLE_NAME, null,
                DefBD.LibroEntry.COLUMN_CODIGO + "=?", new String[]{codigo},
                null, null, null);

        if (cursor.moveToFirst()) {
            // Ya existe, actualizar
            db.update(DefBD.LibroEntry.TABLE_NAME, values,
                    DefBD.LibroEntry.COLUMN_CODIGO + "=?", new String[]{codigo});
            Toast.makeText(this, "Libro actualizado", Toast.LENGTH_SHORT).show();
        } else {
            // No existe, insertar
            db.insert(DefBD.LibroEntry.TABLE_NAME, null, values);
            Toast.makeText(this, "Libro guardado", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        finish();
    }


    public void eliminarLibro(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String codigo = etCodigo.getText().toString();
        db.delete(DefBD.LibroEntry.TABLE_NAME, DefBD.LibroEntry.COLUMN_CODIGO + "=?", new String[]{codigo});
        Toast.makeText(this, "Libro eliminado", Toast.LENGTH_SHORT).show();
        finish();
    }
}
