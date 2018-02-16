package com.navan.alireza.devardevar;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.navan.alireza.devardevar.helper.RequestHelper;


public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Log.i("AlirezaLOg","Hi from splash");
    requestForWriteSDCardPermission();
    requestForFineLocation();
Log.i("AlirezaLOg2","Hi from splash");
  }
public void goToMainActivity(){
  Intent intent=new Intent(SplashActivity.this,MainActivity.class);
  SplashActivity.this.startActivity(intent);
  finish();
}
  private void requestForWriteSDCardPermission() {

    RequestHelper.OnGrantedListener grantedListenerListener1 = new RequestHelper.OnGrantedListener() {
      @Override
      public void onGranted() {
        Log.i("AlirezaLog1","OnGranted");
        G.app.createAppDirectories();
      }
    };

    RequestHelper.OnAlreadyGrantedListener onAlreadyGrantedListener1 = new RequestHelper.OnAlreadyGrantedListener() {
      @Override
      public void onAlreadyGranted() {
        G.app.createOrOpenDatabase();
        Log.i("AlirezaLog1","OnAlreadyGranted");
        goToMainActivity();
      }
    };

    RequestHelper.OnDeniedListener deniedListener1 = new RequestHelper.OnDeniedListener() {
      @Override
      public void onDenied() {
        Log.i("AlirezaLog1","onDenied");
        new AlertDialog.Builder(SplashActivity.this)
                .setTitle("Permission Required")
                .setMessage("Writing to SDCARD required for this app")
                .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    requestForWriteSDCardPermission();
                  }
                })
                .create()
                .show();
      }
    };
    RequestHelper request=new RequestHelper(this);
    request.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, grantedListenerListener1, deniedListener1, onAlreadyGrantedListener1);
  }

  private void requestForFineLocation() {
        Log.i("AlirezaLog","requestForFineLocation");

    RequestHelper.OnGrantedListener grantedListenerListener = new RequestHelper.OnGrantedListener() {
      @Override
      public void onGranted() {
        Log.i("AlirezaLog2","OnGranted");
        goToMainActivity();
      }
    };

    RequestHelper.OnAlreadyGrantedListener onAlreadyGrantedListener = new RequestHelper.OnAlreadyGrantedListener() {
      @Override
      public void onAlreadyGranted() {
        Log.i("AlirezaLo2g","OnAlreadyGranted");
        goToMainActivity();
      }
    };

    RequestHelper.OnDeniedListener deniedListener = new RequestHelper.OnDeniedListener() {
      @Override
      public void onDenied() {
        Log.i("AlirezaLog2","onDenied");
        new AlertDialog.Builder(SplashActivity.this)
                .setTitle("Permission Required")
                .setMessage("ACCESS_FINE_LOCATION required for this app")
                .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    requestForFineLocation();
                  }
                })
                .create()
                .show();
      }
    };
    RequestHelper request=new RequestHelper(this);
    request.request(Manifest.permission.ACCESS_FINE_LOCATION, grantedListenerListener, deniedListener, onAlreadyGrantedListener);
  }
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    RequestHelper.onRequestPermissionResult(requestCode, permissions, grantResults);
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }
}
