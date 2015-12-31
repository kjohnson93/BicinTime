package app.bicintime.wolf.navdrawer;

/**
 * Created by wolf on 12/19/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//maps

//maps


public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        //viewPager.setCurrentItem(1);

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return x;

    }

    class MyAdapter extends FragmentPagerAdapter{

       // private Context context;

        public MyAdapter(FragmentManager fm) {
            super(fm);
           // this.context = context;
        }

        /**
         * Return fragment with respect to Position .
         */

        MapFragmentUnused MapFragment;

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                //case 0 : return MapFragmentUnused = MapFragmentUnused.newInstance();
                case 0 : return new RootFragmentA();
                //case 0: return new PrimaryFragment();
                case 1 : return new RootFragmentB();

            }
            return null;
        }

        @Override
        public int getCount() {

            return 2;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Map"; //deberia estar en strings
                case 1 :
                    return "Plan a Route";

            }
            return null;
        }
    }
}
