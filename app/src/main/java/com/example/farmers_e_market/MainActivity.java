package com.example.farmers_e_market;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout= findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar= findViewById(R.id.toolbar);

        // set toolbar
        setSupportActionBar(toolbar);


        // set drawer open and close
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.OpenDrawer, R.string.CloseDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id==R.id.login){
                     loadFragment(new loginFragment());
                } else if (id==R.id.wallet) {
                    loadFragment(new walletFragment());
                } else if (id==R.id.Customer) {
                    loadFragment(new CustomerSupportFragment());
                } else if (id==R.id.setting) {
                    loadFragment(new SettingFragment());
                } else if (id==R.id.about_us) {
                    loadFragment(new AboutUsFragment());
                } else if (id==R.id.privacy_policy) {
                    loadFragment(new PrivacyPolicyFragment());
                } else if (id==R.id.return_policy) {
                    loadFragment(new ReturnPolicyFragment());
                }
                else{
                    loadFragment(new LogoutFragment());
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        bottomNavigation= findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id==R.id.home){
                    loadFragment(new HomeFragment());
                } else if (id==R.id.category) {
                    loadFragment(new CategoryFragment());
                } else if (id==R.id.my_order) {
                    loadFragment(new MyorderFragment());
                } else if (id==R.id.cart) {
                    loadFragment(new CartFragment());
                } else {
                    loadFragment(new AccountFragment());
                }
                return true;
            }
        });
       FragmentManager fm= getSupportFragmentManager();
       loadFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
       ft.add(R.id.containerDrawer,fragment);
        ft.commit();
    }

    // set toolbar item
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        new MenuInflater(this).inflate(R.menu.toolbar_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId= item.getItemId();

        if(itemId==R.id.notification){
            Toast.makeText(this,"Notification item selected",Toast.LENGTH_SHORT).show();
        } else if (itemId==R.id.search_bar) {
            Toast.makeText(this,"Search item selected",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}