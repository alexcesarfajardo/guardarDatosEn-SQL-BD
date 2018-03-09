package com.uca.alex.cesar.fajardo.practicasql.db;

import android.provider.BaseColumns;


public final class FeedReaderContract {

    //Para evitar que alguien accidentalmente crea una instancia de la clase de contrato
    // hacer que el constructor sea privado
    private FeedReaderContract() {

    }

    // Clase interna que define los contenidos de la tabla
    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

    }


}
