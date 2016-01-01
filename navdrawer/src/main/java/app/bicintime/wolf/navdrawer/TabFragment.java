package app.bicintime.wolf.navdrawer;

/**
 * Created by wolf on 12/19/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

//maps

//maps


public class TabFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;
    private ViewPagerAdapter adapter;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ExpandableListView mNavigationView2;


    public TabFragment(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View rootView =  inflater.inflate(R.layout.tab_layout,container,false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */




        //viewPager.setCurrentItem(1);

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */
        mDrawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) rootView.findViewById(R.id.shitstuff) ; //left navigation drawer
        mNavigationView2 = (ExpandableListView) rootView.findViewById(R.id.right_drawer);  //right navigation drawer

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) rootView.findViewById(R.id.toolbar);
        //android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) findViewById(R.id.searchbar);
        //ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, searchView, R.string.app_name,R.string.app_name);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

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

        mNavigationView2.setAdapter(new NewAdapter(getActivity(), groupItem, childItem));  //aquí podré diseñar toda la vista de la lista, it's gonna take some time

        mNavigationView2.setOnChildClickListener(this);                              //para que se abran las pestañas



        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ViewPagerAdapter(getResources(), getChildFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) adapter.getRegisteredFragment(viewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getActivity(), "Clicked On Child" + v.getTag(),
                Toast.LENGTH_SHORT).show();
        return true;
    }
}

