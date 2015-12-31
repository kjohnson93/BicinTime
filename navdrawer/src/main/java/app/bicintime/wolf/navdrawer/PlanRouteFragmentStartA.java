package app.bicintime.wolf.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by wolf on 12/31/2015.
 */
public class PlanRouteFragmentStartA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_route_start_a, container, false);

        Button button = (Button) rootView.findViewById(R.id.buttonStartA);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlanRouteFragmentStartA2 planRouteFragmentStartA2 = new PlanRouteFragmentStartA2();

                FragmentManager fragmentManager = getChildFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.rootB_framelayout, planRouteFragmentStartA2).commit();

            }
        });



        return rootView;
    }
}
