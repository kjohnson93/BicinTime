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
 * Created by Ratan on 7/29/2015.
 */
public class SocialFragment extends Fragment {


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Fragment_footerB fr1,fr2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        FragmentManager fManager= getChildFragmentManager();


        FragmentTransaction ft = fManager.beginTransaction();  //ESTA PARTE, LA ELIMINO, CREO QUE ES MEJOR DE MOMENTO TENERLO TODO EN UN SOLO FRAGMENTO EN CADA PAGE DEL VIEWPAGER
        fr1= new Fragment_footerB();                            //MAS DE 1 FRAGMENTO, ES UNA LOCURA Y NO SÉ MANEJARLO...
        ft.add(R.id.top, fr1 , "top");

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        fManager.executePendingTransactions();
        */


        View rootView = inflater.inflate(R.layout.social_layout,null);




        /*
        getActivity().findViewById(R.id.buttonFoot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNextFragment();
            }
        });
        */


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        final Button b = (Button) getActivity().findViewById(R.id.buttonTest);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNextFragment();
            }
        });







        /*
        getActivity().findViewById(R.id.buttonFoot).setOnClickListener(new View.OnClickListener() { //ESTO PETA, COMO CONSEGUIR ACCEDER A ELEMENTO FUERA DE LA VISTA DE ESTA CLASE????
            @Override
            public void onClick(View v) {
                enterNextFragment();
            }
        });
        */

    }

    private void enterNextFragment() {


        PlanRouteFragment planRouteFragment = new PlanRouteFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Store the Fragment in stack
        transaction.addToBackStack(null);
        transaction.replace(R.id.rootB_framelayout, planRouteFragment).commit(); //ESTO NO HA SIDO INFLATED, COMO HACER QUE SE HAGA INFLATED?

        //para poder reemplazarlo, antes, necesito que el primer framelayot deba ser subido en un commit, i am fucked... :
        //http://stackoverflow.com/questions/14810348/android-fragment-replace-doesnt-replace-content-puts-it-on-top
        //ALOMEJOR,lo que fallaba era que no reemplaza en la misma id del layout?? im so fucking smart
    }

}
