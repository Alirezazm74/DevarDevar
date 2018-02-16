package com.navan.alireza.devardevar;

import android.content.Intent;
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
import android.widget.Button;

/**
 * Created by Alireza on 15/02/2018.
 */

public class ActivityProfile extends AppCompatActivity {
    private static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.activityMain_bnv_bottomNavigation);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("دیوار ");
        actionbar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        Button btn_createProfile= (Button) findViewById(R.id.Activity_profile_btn_createProfile);
        navigationSetOnClick();

        btn_createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityProfile.this,ActiviyCreateProfile.class);
                ActivityProfile.this.startActivity(intent);
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
                            goToActivityFromActivityProfile(ActivityAdd.class);
                            break;
                        case R.id.menu_bottom_navigation_myprofile :
                            Log.i("AlirezaLog","click on myprofile");
                            break;
                        case R.id.menu_bottom_navigation_aghahe :
                            Log.i("AlirezaLog","click on aghahe");
                            goToActivityFromActivityProfile(MainActivity.class);
                            break;
                        case R.id.menu_bottom_navigation_more :
                            Log.i("AlirezaLog","click on more");
                            goToActivityFromActivityProfile(ActivityMore.class);
                            break;
                        case R.id.menu_bottom_navigation_search :
                            Log.i("AlirezaLog","click on search");
                            goToActivityFromActivityProfile(ActivitySearch.class);
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

    public  void goToActivityFromActivityProfile(Class clazz){
        Intent intent=new Intent(ActivityProfile.this,clazz);
        ActivityProfile.this.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
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
