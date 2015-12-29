package app.bicintime.wolf.navdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener{

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentManager fragmentManager;
    FragmentTransaction mFragmentTransaction;

    Fragment_footer fr1,fr2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;


/**
 * Lets inflate the very first fragment
 * Here , we are inflating the TabFragment as the first Fragment
 */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.nav_item_sent) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new SentFragment()).commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_inbox) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        //android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) findViewById(R.id.searchbar);
        //ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, searchView, R.string.app_name,R.string.app_name);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
               R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

/*
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fr1= new Fragment_footer();
        ft.add(R.id.top, fr1 , "top");
        fr2 = new Fragment_footer();
        ft.add(R.id.bottom, fr2, "bottom");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();

*/      Log.d("BICIN", "ESTOY ENTRANDO");
        Log.d("BICIN", "ESTOY ENTRANDO");

        fragmentManager = getSupportFragmentManager(); //me fallaba esta línia, ... utilizaba creo getFragmentManager directo, porque fallaba???

        MapFragmentUnused mf = (MapFragmentUnused) fragmentManager.findFragmentById(R.id.map);

      /*  mf.getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Log.d("BICIN", "ESTOY ENTRANDO 2");
                //fragmentManager = getSupportFragmentManager(); //me fallaba esta línia, ... utilizaba creo getFragmentManager directo, porque fallaba???

                MapFragmentUnused mf2 = (MapFragmentUnused) fragmentManager.findFragmentById(R.id.map);

                Log.d("BICIN", "ESTOY ENTRANDO 2");
                Log.d("BICIN", "ESTOY ENTRANDO 2");

                mf2.MarkerClicked();


                return false;
            }
        });*/

    }

    @Override
    public boolean onMarkerClick(Marker marker) {



        fragmentManager = getSupportFragmentManager(); //me fallaba esta línia, ... utilizaba creo getFragmentManager directo, porque fallaba???

        MapFragmentUnused mf = (MapFragmentUnused) fragmentManager.findFragmentById(R.id.map);

        Log.d("BICIN", "ESTOY ENTRANDO");

        mf.MarkerClicked();

        //MapFragmentUnused mf = (MapFragmentUnused)  fragmentManager.findFragmentById(R.id.map_fragment);

        //MapFragmentUnused mf = (MapFragmentUnused) fragmentManager.findFragmentById(R.id.map);


        return false;
    }
}
