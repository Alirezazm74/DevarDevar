package com.navan.alireza.devardevar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.navan.alireza.devardevar.helper.MyDatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class G extends Application {
  public static G app;
  public static Context context;
  public static Activity mainactivity;

  public static SQLiteDatabase database;
  public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
  public static final String BRAND_DIR = SDCARD + "/AlirezaDev";
  public static final String APP_DIR = BRAND_DIR + "/DivirDivar";
  public static final String DB_DIR = APP_DIR + "/db";

  @Override
  public void onCreate() {
    super.onCreate();
context=getApplicationContext();
    app = this;
  }

  public void createAppDirectories() {
    File dbDir = new File(DB_DIR);

    if (!dbDir.exists()) {
      boolean wasCreated = dbDir.mkdirs();
      try{
        dbDir.createNewFile();
        copyFromAssets(getBaseContext().getAssets().open("divar.sqlite"),
                new FileOutputStream(DB_DIR+"/divar.sqlite"));

      }catch(IOException e){

        e.printStackTrace();
      }
      Log.i("AlirezaLog","directory make now");
    }
    else
      Log.i("AlirezaLog","directory exist");

    createOrOpenDatabase();
  }

  public void createOrOpenDatabase() {
    if (database != null) {
      return;
    }

    MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
    database = dbHelper.getWritableDatabase();
    Log.i("AlirezaLOg","database create");
  }

  public static String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
  public static String DIR_APP = DIR_SDCARD + "/AlirezaDev2/databeas";



  public void copyFromAssets(InputStream inputStream, OutputStream outputStream) throws IOException{

    byte[] buffer=new byte[100];
    int length;
    while((length=inputStream.read(buffer))>0){
      outputStream.write(buffer,0,length);
    }
    inputStream.close();
    outputStream.close();


  }

}





