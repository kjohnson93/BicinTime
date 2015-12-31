package app.bicintime.wolf.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by wolf on 12/26/2015.
 */
public class PlanRouteFragment extends Fragment {

    //public empty constrctor may solve the problem??
    public PlanRouteFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_route, container, false);


        Button b = (Button) rootView.findViewById(R.id.buttonPlanRoute);

        b.setText("Changed hahahaha");

        Log.d("BUTTON", "ENTERING #1");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BUTTON", "ENTERING #2");

                //SentFragment sentFragment = new SentFragment();
                PlanRouteFragmentStartA planRouteFragmentStartA = new PlanRouteFragmentStartA();

                FragmentManager fragmentManager = getChildFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);

                //fragmentTransaction.replace(R.id.rootB_framelayout, sentFragment).commit();
                fragmentTransaction.replace(R.id.rootB_framelayout, planRouteFragmentStartA).commit();


            }
        });
            /*
        Button b = (Button) getActivity().findViewById(R.id.buttonPlanRoute);
        //El problema esta en que intento a acceder antes de que se cree, aun cuando la actividad se finaliza, solo finaliza el primer fragmento
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        return rootView;
    }
}
