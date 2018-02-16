package com.navan.alireza.devardevar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.navan.alireza.devardevar.fragment.fragment1;
import com.navan.alireza.devardevar.fragment.fragment2;
import com.navan.alireza.devardevar.fragment.fragment3;
import com.navan.alireza.devardevar.fragment.fragment4;
import com.navan.alireza.devardevar.helper.DBHelper;
import com.navan.alireza.devardevar.widget.UTab;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    private View decorView;
    public  static DBHelper dbHelper;

    private static BottomNavigationView mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        UTab tab=new UTab(this,R.id.viewPager,R.id.tabLayout);
        tab.add(fragment1.class,"همه");
        tab.add(fragment2.class,"ابزارآلات");
        tab.add(fragment3.class,"وسایل نقلیه");
        tab.add(fragment4.class,"لوازم خانگی");

        mBottomBar= (BottomNavigationView) findViewById(R.id.activityMain_bnv_bottomNavigation);
        mBottomBar.getMenu().getItem(4).setChecked(true);
        G.mainactivity=MainActivity.this;

        decorView = getWindow().getDecorView();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("دیوار ");
        actionbar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        navigationSetOnClick();
        dbHelper=new DBHelper(G.database);

    }

    private void navigationSetOnClick(){
if(mBottomBar!=null){
        mBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.menu_bottom_navigation_newAghahe :
                        Log.i("AlirezaLog","click on new aghahe");
                        goToActivityFromMain(ActivityAdd.class);
                        break;
                    case R.id.menu_bottom_navigation_myprofile :
                        Log.i("AlirezaLog","click on myprofile");
                        goToActivityFromMain(ActivityProfile.class);
                        break;
                    case R.id.menu_bottom_navigation_aghahe :
                        Log.i("AlirezaLog","click on aghahe");
                        break;
                    case R.id.menu_bottom_navigation_more :
                        Log.i("AlirezaLog","click on more");
                        goToActivityFromMain(ActivityMore.class);
                        break;
                    case R.id.menu_bottom_navigation_search :
                        Log.i("AlirezaLog","click on search");
                        goToActivityFromMain(ActivitySearch.class);
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

    public  void goToActivityFromMain(Class clazz){
        Intent intent=new Intent(MainActivity.this,clazz);
        MainActivity.this.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mBottomBar.getMenu().getItem(4).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.menu_about:
                makeText(MainActivity.this, "About Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_add:
                makeText(MainActivity.this, "add Clicked", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this,ActivityAdd.class);
                MainActivity.this.startActivity(intent);
                return true;


        }

        return super.onOptionsItemSelected(item);
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

