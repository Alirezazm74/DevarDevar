package com.navan.alireza.devardevar.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.navan.alireza.devardevar.G;


public class MyDatabaseHelper extends SQLiteOpenHelper {
  public static final String DB_NAME = "divar.sqlite";
  public static final int DB_VERSION =1;

  public MyDatabaseHelper(Context context) {
    super(context, G.DB_DIR + "/" + DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
//    createColorsTable(db);
//    createtepesTable(db);
//    createSubTepesTable(db);


  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion == 1 && newVersion == 2) {
//      createColorsTable(db);
//      createtepesTable(db);
//      createSubTepesTable(db);
    }
  }

  private void createColorsTable(SQLiteDatabase db) {
    String query =
      "CREATE TABLE 'colors' (" +
        "'colorId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
        "'colorName' TEXT " +
        ")";
    db.execSQL(query);


  }

  private void createtepesTable(SQLiteDatabase db) {
    String query =
            "CREATE TABLE 'types' (" +
                    "'typeId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                    "'typeName' TEXT " +
                    ")";
    db.execSQL(query);


  }
  private void createSubTepesTable(SQLiteDatabase db) {
    String query =
            "CREATE TABLE 'subTypes' (" +
                    "'subTypeId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                    "'subTypeName' TEXT " +
                    ")";
    db.execSQL(query);


  }
}