package com.uca.alex.cesar.fajardo.practicasql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        Log.i("ID", String.valueOf(newRowId));

        //Proceso de registro de una tabla

        db = mDbHelper.getReadableDatabase();

        // Definir una proyección que especifique qué columnas de la base de datos
        // lo usarás después de esta consulta.

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };

            // Filtrar resultados DONDE "titulo" = "nombre"
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "nombre" };

            //Cómo desea que se clasifiquen los resultados en el Cursor resultante
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // la tabla para consultar
                projection,                               // columnas para retornar
                selection,                                // columnas para la clausula WHERE
                selectionArgs,                            // Valores de la clausula WHERE
                null,                                     // no agrupar las filas
                null,                                     // no filtrar por grupos de filas
                sortOrder                                 // orden de clasificacion
        );

        //Solo devuelve el primer registro
        /*
        c.moveToFirst();
        String title = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
        String subtitle = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
        Log.i("MainActivity", "Title: " + title + " Subtitle = " + subtitle);
        */

        //Para recorrer todos los registros con while

        while (c.moveToNext()){
            String title = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            String subtitle = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
            Log.i("MainActivity", "Title: " + title + " Subtitle = " + subtitle);
        }


        /*
        for(int i = 0; i<c.getCount(); i++){

            String title = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            String subtitle = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
            Log.i("MainActivity", "Title: " + title + " Subtitle = " + subtitle);

        } */

    }
}
