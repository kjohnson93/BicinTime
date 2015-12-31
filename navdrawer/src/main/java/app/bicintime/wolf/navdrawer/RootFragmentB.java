package app.bicintime.wolf.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by shahabuddin on 6/6/14.
 */
public class RootFragmentB extends Fragment implements OnBackPressListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.rootb_layout, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.addToBackStack(null);

        PlanRouteFragment planRouteFragment = new PlanRouteFragment();




        fragmentTransaction.replace(R.id.rootB_framelayout, planRouteFragment).commit();

        //fragmentTransaction = fragmentManager.beginTransaction();

        //fragmentTransaction.replace(R.id.root_framelayout, planRouteFragment).commit();



        return rootView;


    }

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }
}
