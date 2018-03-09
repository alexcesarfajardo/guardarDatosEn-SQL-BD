package com.uca.alex.cesar.fajardo.practicasql;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uca.alex.cesar.fajardo.practicasql.db.FeedReaderContract;
import com.uca.alex.cesar.fajardo.practicasql.db.FeedReaderDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);

        // Obtiene el repositorio de datos en modo de escritura
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Crea un nuevo mapa de valores, donde los nombres de las columnas son las claves
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "nombre");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "apellido");

        // Inserte la nueva fila, devolviendo el valor de la clave principal de la nueva fila
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

    }
}
