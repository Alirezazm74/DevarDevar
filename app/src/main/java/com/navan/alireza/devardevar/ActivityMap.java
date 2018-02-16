package com.navan.alireza.devardevar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

public class ActivityMap extends AppCompatActivity {
    GoogleMap map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        loadMap();
    }

    private void loadMap()
    {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()) == 0) {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                    LatLng point;
                    if(ActiviyCreateProfile.chooseLocation==null){
                        point = new LatLng(35.691063, 51.407941);
                    }else {
                        point=ActiviyCreateProfile.chooseLocation;
                        map.addMarker(new MarkerOptions().position(point));
                    }
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 10));
                    map.getUiSettings().setZoomControlsEnabled(true);
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(true);
                    map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                        @Override
                        public void onMapLongClick(LatLng latLng) {
                            map.clear();
                            Log.i("AzL location is :", latLng.latitude + "  AA");
                            map.addMarker(new MarkerOptions().position(latLng).title("A.com"));
                            ActiviyCreateProfile.chooseLocation=latLng;
                        }
                    });
                }
            });
        }
    }
}


