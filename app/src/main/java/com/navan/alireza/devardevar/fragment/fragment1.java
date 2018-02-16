package com.navan.alireza.devardevar.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navan.alireza.devardevar.G;
import com.navan.alireza.devardevar.R;
import com.navan.alireza.devardevar.recyclerView.Kala;
import com.navan.alireza.devardevar.recyclerView.KalaRecycleStruct;
import com.navan.alireza.devardevar.recyclerView.KalaRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Alireza on 13/02/2018.
 */

public class fragment1 extends Fragment {
    public static RecyclerView recyclerView;
    public static ArrayList<KalaRecycleStruct> kalas;
    public static KalaRecyclerAdapter kalaRecyclerAdapter;
    public static boolean kalasListChanged=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_1,container,false);

        recyclerView= (RecyclerView)view. findViewById(R.id.list_kala);
        recyclerView.setLayoutManager(new LinearLayoutManager(G.mainactivity));
        kalas = new ArrayList<KalaRecycleStruct>();
        kalasListChanged=true;

        return view;
    }

    private void loadkala(){
        String query="SELECT * FROM kala";
        Cursor cursor= G.database.rawQuery(query,null);
        Log.i("AZ Log from loadKala","query run");


        while (cursor.moveToNext()) {
            Kala kala=new Kala(KalaRecycleStruct.TYPE_KALA);
            kala.init(cursor);
            kalas.add(kala);
        }
        cursor.close();
        kalaRecyclerAdapter = new KalaRecyclerAdapter(kalas,G.mainactivity);
        recyclerView.setAdapter(kalaRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (kalasListChanged){
            kalas.clear();
            loadkala();
            kalasListChanged=false;
        }
    }
}
