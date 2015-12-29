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
 * Created by wolf on 12/27/2015.
 */
public class RootFragmentA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roota_layout, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.addToBackStack(null);

        MapFragmentUnused mapFragment = MapFragmentUnused.newInstance();
        MapFragmentA mapFragmentB = new MapFragmentA();
        fragmentTransaction.replace(R.id.rootA_framelayout, mapFragmentB).commit();


        return rootView;



    }
}
