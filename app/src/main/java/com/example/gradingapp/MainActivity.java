package com.example.gradingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

/*  Assignment2 - GradingApp
 *   PROG8480 Sec1
 *   Hyungbum Kim
 *   2022-08-10
 */


public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        // Get Widgets using id for each Widgets and assign it into matched Widgets variable
        // Widgets are already on heap, so I can access them by the same method.

        navView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.nav_open,R.string.nav_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
    }

    // Setting menus for drawer.
    private void SetNavigationDrawer()
    {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                int itemId = item.getItemId();
                if(itemId == R.id.nav_enter_grades)
                {
                    frag = new EnterGradesFragment();
                }
                else if(itemId == R.id.nav_list_students)
                {
                    frag = new ListStudentFragment();
                }
                else if(itemId == R.id.nav_enter_improvement)
                {
                    frag = new EnterImpFragment();
                }
                else if(itemId == R.id.nav_search_grade)
                {
                    frag = new SearchGradeFragment();
                }

                if(frag != null)
                {
                    FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
                    frgTrans.replace(R.id.frame, frag);
                    frgTrans.commit();
                    mDrawerLayout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }

    //Open Drawer when a toggle button is pushed
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}