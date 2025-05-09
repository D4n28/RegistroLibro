package com.example.mainactivity;

import android.provider.BaseColumns;

public final class DefBD {
    private DefBD() {}

    public static class LibroEntry implements BaseColumns {
        public static final String TABLE_NAME = "Libro";
        public static final String COLUMN_CODIGO = "codigo";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_AUTOR = "autor";
        public static final String COLUMN_EDITORIAL = "editorial";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LibroEntry.TABLE_NAME + " (" +
                    LibroEntry._ID + " INTEGER PRIMARY KEY," +
                    LibroEntry.COLUMN_CODIGO + " TEXT," +
                    LibroEntry.COLUMN_NOMBRE + " TEXT," +
                    LibroEntry.COLUMN_AUTOR + " TEXT," +
                    LibroEntry.COLUMN_EDITORIAL + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LibroEntry.TABLE_NAME;
}