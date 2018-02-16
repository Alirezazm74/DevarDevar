package com.navan.alireza.devardevar.helper;


import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.navan.alireza.devardevar.G;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DBHelper {
  private SQLiteDatabase db;

  public DBHelper(SQLiteDatabase db) {
    this.db = db;
  }

  public void truncate(String table) {
    db.execSQL("DELETE FROM " + table);
  }

  public void dumpCursor(Cursor cursor) {
    int columnCount = cursor.getColumnCount();
    int index = 0;


    while (cursor.moveToNext()) {
      index++;
      String rowStr = "";
      for (int i=0; i<columnCount; i++) {
        int type = cursor.getType(i);
        String value = "";

        switch (type) {
          case Cursor.FIELD_TYPE_NULL:
            value = "NULL";
            break;
          case Cursor.FIELD_TYPE_INTEGER:
            value = "" + cursor.getInt(i);
            break;
          case Cursor.FIELD_TYPE_FLOAT:
            value = "" + cursor.getFloat(i);
            break;
          case Cursor.FIELD_TYPE_STRING:
            value = cursor.getString(i);
            break;
        }

        rowStr += value + " | ";
      }

      Log.i("LOG", "#" + index + ": " + rowStr);
    }
  }

  public ArrayList getArrayList(Cursor cursor) {
    ArrayList<String>values=new ArrayList<>();
    int columnCount = cursor.getColumnCount();
    int index = 0;

    while (cursor.moveToNext()) {
      index++;
      String rowStr = "";
      for (int i=0; i<columnCount; i++) {
        int type = cursor.getType(i);
        String value = "";

        switch (type) {
          case Cursor.FIELD_TYPE_NULL:
            value = "NULL";
            break;
          case Cursor.FIELD_TYPE_INTEGER:
            value = "" + cursor.getInt(i);
            break;
          case Cursor.FIELD_TYPE_FLOAT:
            value = "" + cursor.getFloat(i);
            break;
          case Cursor.FIELD_TYPE_STRING:
            value = cursor.getString(i);
            values.add(value);
            break;
        }

        rowStr += value + " | ";
      }

      Log.i("LOG", "#" + index + ": " + rowStr);

    }
    return values;
  }
  public Object[] createObject(Class clazz, Cursor cursor) {
    int columnCount = cursor.getColumnCount();
    Object[] objects = new Object[cursor.getCount()];

    int index = 0;
    while (cursor.moveToNext()) {
      Object object = null;

      try {
        object = clazz.newInstance();
        objects[index] = object;
      } catch (InstantiationException e) {
        //e.printStackTrace();
      } catch (IllegalAccessException e) {
        //e.printStackTrace();
      }

      for (int i=0; i<columnCount; i++) {
        int type = cursor.getType(i);
        String fieldName = cursor.getColumnName(i);
        try {
          Field field = clazz.getDeclaredField(fieldName);

          switch (type) {
            case Cursor.FIELD_TYPE_NULL:
              field.set(object, null);
              break;
            case Cursor.FIELD_TYPE_INTEGER:
              field.set(object, cursor.getInt(i));
              break;
            case Cursor.FIELD_TYPE_FLOAT:
              field.set(object, cursor.getFloat(i));
              break;
            case Cursor.FIELD_TYPE_STRING:
              field.set(object, cursor.getString(i));
              break;
          }
        } catch (IllegalAccessException e) {
          //e.printStackTrace();
        } catch (NoSuchFieldException e) {
          //e.printStackTrace();
        }
      }

      index++;
    }

    return objects;
  }

  public int insert(String table, String[] columns, Object[] values) {
    String columnsStr = joinColumns(columns);
    String valuesStr = joinValues(values);

    try {
      db.execSQL("INSERT INTO " + table + "(" + columnsStr + ") VALUES (" + valuesStr + ")");
      Log.i("AlirezaLog","insert query="+"INSERT INTO " + table + "(" + columnsStr + ") VALUES (" + valuesStr + ")");
      Log.i("LOG DatabaseInsert","LOG DatabaseInsert");
    } catch (SQLiteConstraintException e) {
      Log.e("LOGErorInDatabaseInsert", e.getMessage());
    }

    return getLastInsertId();
  }

  public void delete(String table, String where) {
    db.execSQL("DELETE FROM " + table + " WHERE " + where);
  }

  public void update(String table, String[] columns, Object[] values, String where) {
    String updatesStr = joinColumnValuePair(columns, values);

    try {
      db.execSQL("UPDATE " + table + " SET " + updatesStr + " WHERE " + where);
    } catch (SQLiteConstraintException e) {
      Log.e("LOG", e.getMessage());
    }
  }

  private String joinColumnValuePair(String[] columns, Object[] values) {
    String[] updates = new String[columns.length];

    for (int i=0; i<columns.length; i++) {
      String column = columns[i];
      String value = quote(values[i].toString());

      updates[i] = column + "=" + value;
    }

    String updatesStr = TextUtils.join(", " , updates);
    return updatesStr;
  }

  public int getLastInsertId() {
    Cursor cursor = G.database.rawQuery("SELECT last_insert_rowid() AS lastId", null);
    cursor.moveToFirst();
    int insertId = cursor.getInt(cursor.getColumnIndex("lastId"));
    return insertId;
  }

  private String joinValues(Object[] values) {
    String[] valuesAsStr = new String[values.length];
    for (int i=0; i<values.length; i++) {
      String value = values[i].toString();
      valuesAsStr[i] = quote(value);
    }

    String out = TextUtils.join(", " , valuesAsStr);
    return out;
  }

  private String joinColumns(String[] columns) {
    return TextUtils.join(", " , columns);
  }

  private String quote(String value) {
    return "'" + value + "'";
  }
}
