package com.navan.alireza.devardevar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.navan.alireza.devardevar.fragment.fragment1;
import com.navan.alireza.devardevar.recyclerView.Kala;
import com.navan.alireza.devardevar.recyclerView.KalaRecycleStruct;
import com.navan.alireza.devardevar.recyclerView.KalaRecyclerAdapter;

import java.util.ArrayList;


/**
 * Created by Alireza on 12/02/2018.
 */

public class ActivityKalaDetials extends AppCompatActivity{
private String kalaId="";
    public static ArrayList<KalaRecycleStruct> sameKalaList;
    KalaRecyclerAdapter sameKalaAdapter;
    RecyclerView samekalaRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kala_detials);
        samekalaRecyclerView= (RecyclerView) findViewById(R.id.ActivityKalaDetials_likeKala_recyclerview);
        kalaId=getIntent().getExtras().getString("kalaId");
        Log.i("AlirezaLog","KalaId is :"+kalaId);
        sameKalaList=new ArrayList<>();
        fun_loadSameKala();
    }
    private void fun_loadSameKala(){

        String query="SELECT * FROM kala";
        Cursor cursor= G.database.rawQuery(query,null);
        Log.i("AZ Log from loadKala","query run");
        samekalaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        while (cursor.moveToNext()) {
            Kala kala=new Kala(KalaRecycleStruct.TYPE_KALA);
            kala.init(cursor);
            sameKalaList.add(kala);
        }
        cursor.close();
        sameKalaAdapter = new KalaRecyclerAdapter(sameKalaList,ActivityKalaDetials.this);
        samekalaRecyclerView .setAdapter(fragment1.kalaRecyclerAdapter);
//        sameKalaAdapter=new KalaRecyclerAdapter(sameKalaList,);
    }
}

