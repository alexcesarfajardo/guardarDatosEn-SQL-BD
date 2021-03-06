package com.uca.alex.cesar.fajardo.practicasql.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FeedReaderDbHelper extends SQLiteOpenHelper {

    //Si cambia el esquema de la base de datos, debe incrementar la versión de la base de datos.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    //Para crear una tabla
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + " )";
    //Podemos repetir esto para crear una segunda tabla, lo añadimos en onCreate y listo


    //Para eliminar campos de la tabla y quedar vacia
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creando una tabla
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Esta base de datos es solo un caché para datos en línea,
        // por lo que su política de actualización es simplemente
        // descartar los datos y comenzar de nuevo

        //Borrar los datos de una tabla
        db.execSQL(SQL_DELETE_ENTRIES);
        //y volvemos a crear otra tabla vacia de ser necesario
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
