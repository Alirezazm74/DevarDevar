package com.navan.alireza.devardevar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Alireza on 15/02/2018.
 */

public class ActiviyCreateProfile extends AppCompatActivity {
    GoogleMap map;
    AppCompatEditText edt_name;
    AppCompatEditText edt_family;
    AppCompatEditText edt_phoneNumber;
    AppCompatEditText edt_pass;
    AppCompatEditText edt_repeatPass;
    AppCompatEditText edt_email;
    AppCompatEditText edt_address;
    Button confirm;
    public static LatLng chooseLocation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        edt_name=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_name);
        edt_family=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_family);
        edt_phoneNumber=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_phoneNumber);
        edt_pass=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_pass);
        edt_repeatPass=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_repeatPass);
        edt_email=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_email);
        edt_address=(AppCompatEditText)findViewById(R.id.Activity_create_Profile_edt_address);
        confirm=(Button)findViewById(R.id.Activity_create_Profile_btn_confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValues())
                    if(checkServer())
                        sendToServer();
            }
        });
    }
    private void sendToServer(){
    }
    private boolean checkValues(){
        Boolean returnValue=true;
        if(edt_name.getText().toString().length()<2){
            returnValue=false;
            Toast.makeText(this,"نام خود را درست وارد نکرده اید!",Toast.LENGTH_SHORT).show();
        }else if(edt_family.getText().toString().length()<2){
            returnValue=false;
            Toast.makeText(this,"نام خانوادگی خود را درست وارد نکرده اید!",Toast.LENGTH_SHORT).show();
        }else if(edt_phoneNumber.getText().toString().length()!=11){
            returnValue=false;
            Toast.makeText(this,"شماره موبایل خود را درست وارد نکرده اید!",Toast.LENGTH_SHORT).show();
        }else if(edt_pass.getText().toString().length()<4){
            returnValue=false;
            Toast.makeText(this,"رمز عبور باید بیشتر از چهار رقم باشد!",Toast.LENGTH_SHORT).show();
        }else if(!edt_repeatPass.getText().toString().equals(edt_pass.getText().toString())){
            returnValue=false;
            Toast.makeText(this,"رمز عبور با تکرار آن مطابقت ندارد!",Toast.LENGTH_SHORT).show();
        }else if((!edt_email.getText().toString().contains("@"))&&edt_email.getText().toString().length()<4){
            returnValue=false;
            Toast.makeText(this,"ایمیل خود را به درتی وارد نکرده اید",Toast.LENGTH_SHORT).show();
        }else if(edt_address.getText().toString().length()<5){
            returnValue=false;
            Toast.makeText(this,"لطفا آدرس را وارد کنید",Toast.LENGTH_SHORT).show();
        }else if(chooseLocation==null){
            returnValue=false;
            Toast.makeText(this,"لطفا بر روی نقشه آدرس خود را مشخص کنید",Toast.LENGTH_SHORT).show();
        }
        return returnValue;
    }
    private boolean checkServer(){
        return true;
    }
    private void loadMap(){


        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()) == 0) {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Activity_create_Profile_frag_map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                    map.clear();
                    LatLng point;
                    float zoom;
                    if(chooseLocation==null){
                        point = new LatLng(35.691063, 51.407941);
                        zoom=10;
                    }else {
                        point=chooseLocation;
                        map.addMarker(new MarkerOptions().position(point));
                        zoom=15;
                    }
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, zoom));
                    map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            Intent intent=new Intent(ActiviyCreateProfile.this,ActivityMap.class);
                            ActiviyCreateProfile.this.startActivity(intent);
                        }
                    });
                }
            });

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadMap();
    }
}
