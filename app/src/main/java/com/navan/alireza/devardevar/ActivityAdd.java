package com.navan.alireza.devardevar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.navan.alireza.devardevar.fragment.fragment1;
import com.navan.alireza.devardevar.recyclerView.Kala;
import com.navan.alireza.devardevar.recyclerView.KalaRecycleStruct;

import java.util.ArrayList;

import static com.navan.alireza.devardevar.MainActivity.dbHelper;

/**
 * Created by Alireza on 08/02/2018.
 */

public class ActivityAdd extends AppCompatActivity {
    private static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        Button btnOk= (Button) findViewById(R.id.ActivityAdd_btn_ok);
        final EditText edt_description= (EditText) findViewById(R.id.ActivityAdd_edt_description);
        final EditText edt_name= (EditText) findViewById(R.id.ActivityAdd_edt_name);
        final EditText edt_price= (EditText) findViewById(R.id.ActivityAdd_edt_price);
        final EditText edt_profileInfo= (EditText) findViewById(R.id.ActivityAdd_edt_profileInfo);
        final Spinner sp_type= (Spinner) findViewById(R.id.activityAdd_sp_type);
        final Spinner sp_subtype= (Spinner) findViewById(R.id.activityAdd_sp_subtype);
        final Spinner sp_colors= (Spinner) findViewById(R.id.activityAdd_sp_colors);
        final Spinner sp_application = (Spinner) findViewById(R.id.activityAdd_sp_application);
        final Spinner sp_dimension = (Spinner) findViewById(R.id.activityAdd_sp_dimension);
        final Spinner sp_technology = (Spinner) findViewById(R.id.activityAdd_sp_technology);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.activityAdd_bnv_bottomNavigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("دیوار ");

        actionbar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        init_sp(sp_subtype,"SELECT * FROM subType","لطفا زیر نوع را انتخاب کنید");
        init_sp(sp_colors,"SELECT * FROM colors","لطفا رنگ را انتخاب کنید");
        init_sp(sp_type,"SELECT * FROM type","لطفا نوع را انتخاب کنید");
        init_sp(sp_application,"SELECT * FROM application","لطفا کاربرد را انتخاب کنید");
        init_sp(sp_dimension,"SELECT * FROM dimension","لطفا ابعاد را انتخاب کنید");
        init_sp(sp_technology,"SELECT * FROM technology","لطفا تکنولوژی  را انتخاب کنید");
        navigationSetOnClick();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edt_name.getText().toString();
                String description =  edt_description.getText().toString();
                String price =  edt_price.getText().toString();
                String profileInfo = edt_profileInfo.getText().toString();
                String image="image1";
                Log.i("Allireza Log","type index is"+sp_type.getSelectedItemId());
                int id=0;
                id=MainActivity.dbHelper.insert("kala",new String[]
                                {"name","typeId","colorId","subTypeId","dimensionsId","technologyId", "applicationId","price","description","image"},
                        new Object[]{name,sp_type.getSelectedItem(),sp_colors.getSelectedItem(),sp_subtype.getSelectedItem(),sp_dimension.getSelectedItem(),
                                sp_technology.getSelectedItem(),sp_application.getSelectedItem(),price,description,image});
                Toast.makeText(getApplicationContext()," آگهی شما ثبت شد ",Toast.LENGTH_SHORT).show();
                if (id!=0) {
                    addKalaToList(id);
                }
                finish();
            }
        });

    }
    private void navigationSetOnClick(){
        if(bottomNavigationView!=null){
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){

                        case R.id.menu_bottom_navigation_newAghahe :
                            Log.i("AlirezaLog","click on new aghahe");

                            break;
                        case R.id.menu_bottom_navigation_myprofile :
                            Log.i("AlirezaLog","click on myprofile");
                            goToActivityFromActivityAdd(ActivityProfile.class);
                            break;
                        case R.id.menu_bottom_navigation_aghahe :
                            Log.i("AlirezaLog","click on aghahe");
                            goToActivityFromActivityAdd(MainActivity.class);
                            break;
                        case R.id.menu_bottom_navigation_more :
                            Log.i("AlirezaLog","click on more");
                            goToActivityFromActivityAdd(ActivityMore.class);
                            break;
                        case R.id.menu_bottom_navigation_search :
                            Log.i("AlirezaLog","click on search");
                            goToActivityFromActivityAdd(ActivitySearch.class);
                            break;

                    }
                    return true;
                }
            });
        }
        else {
            Log.d("A;irezaLog","mBottomBar is null");
        }
    }

    private void goToActivityFromActivityAdd(Class clazz){
        Intent intent=new Intent(ActivityAdd.this,clazz);
        ActivityAdd.this.startActivity(intent);
    }
    private void addKalaToList(int id){

        String query="SELECT * FROM kala WHERE kalaId="+id;
        Log.i("AlirezaLog","load one record from database with query ="+query);
        Cursor cursor= G.database.rawQuery(query,null);

        while (cursor.moveToNext()) {
            Kala kala=new Kala(KalaRecycleStruct.TYPE_KALA);
            kala.init(cursor);
            fragment1.kalas.add(kala);

        }
        cursor.close();
        fragment1.kalasListChanged=true;
    }
    private void init_sp(Spinner sp,String query,String defult){

        ArrayList<String> types=new ArrayList<>();
        Cursor cursor=G.database.rawQuery(query,null);
        types= dbHelper.getArrayList(cursor);
        types.add(0,defult);


        ArrayAdapter arrayAdapter=
                new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,types);
        sp.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                    Snackbar.make(decorView, "Submitted: " + query, Snackbar.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                    Snackbar.make(decorView, "Changed to: " + newText, Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
