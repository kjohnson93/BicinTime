package app.bicintime.wolf.navdrawer;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, ExpandableListView.OnChildClickListener {



    FragmentManager fragmentManager;


    Fragment_footer fr1,fr2;

    private TabFragment tabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         *Setup the DrawerLayout and NavigationView
         */




        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
        /*
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();  //el fragmento padre de todos que engloba al resto de fragmentos
        */
        /**
         * Setup click events on the Navigation View Items.
         */

        /*
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {  //esto solo sirve para probar abrir un par de paginas desde el Left navigation drawer
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
        */

        /**
         * Setup Drawer Toggle of the Toolbar
         */



        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            initScreen();

        } else {
            // restoring the previously created fragment
            // and getting the reference
            tabFragment = (TabFragment) getSupportFragmentManager().getFragments().get(0);
        }

        ArrayList<String> groupItem = new ArrayList<String>();  // a partir de aquí empieza lo que he descubierto para el right navigation drawer
        ArrayList<Object> childItem = new ArrayList<Object>();

        groupItem.add("TechNology");                            //hay que darle datos al right nav drawer sino dará errores nullpointer
        groupItem.add("Mobile");

        ArrayList<String> child = new ArrayList<String>();
        child.add("Java");
        child.add("Drupal");
        child.add(".Net Framework");
        child.add("PHP");
        childItem.add(child);

        child = new ArrayList<String>();
        child.add("Android");
        child.add("Window Mobile");
        child.add("iPHone");
        child.add("Blackberry");
        childItem.add(child);



    }

    private void initScreen() {
        // Creating the ViewPager container fragment once
        tabFragment = new TabFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rootB_framelayout, tabFragment).commit();
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

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) { //metodo override por la interfaz de clicar para que se abran las pestañas
        Toast.makeText(this, "Clicked On Child" + v.getTag(),
                Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    public void onBackPressed() {
        if (!tabFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
            super.onBackPressed();

        } else {
            // carousel handled the back pressed task
            // do not call super
        }

    }
}
