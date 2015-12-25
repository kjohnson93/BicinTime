package app.bicintime.wolf.navdrawer;

/**
 * Created by wolf on 12/23/2015.
 */

import android.app.admin.SystemUpdatePolicy;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends SupportMapFragment {

    LatLng myCoordinates = new LatLng(41.387128, 2.168565);
    final LatLng WTC = new LatLng(41.372203, 2.180496);       //Coordenadas de la estación bicing de mi trabajo
    GoogleMap googleMap;                                       //objeto de tipo googleMap que es el mapa que voy a mostrar

    SupportMapFragment mSupportMapFragment;                     //Fragmento que envolvera al googleMap
    CameraUpdate yourLocation;                                  //Para colocar el area de visión

    Fragment_footer fr1,fr2;


    //El constructor normal de un objeto que extiende SupportMapFragment no deja crear un objeto es porque no deja pasarle argumentos mientras se construye porque deben tener un constructor vacio por defecto
    //pass the arguments to the fragment while constructing it aswel: static method. REASON why we not pass arguments to construcot its because they must have a empty constructor by default
    public static MapFragment newInstance()                 //Con un constructor metodo static si que se puede pasar argumentos
    {
        MapFragment frag = new MapFragment();


        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState); //SIN ESTA LINEA CRASHEA EL CODIGO -> NULLPOINTER EXCEPTION
        View root = inflater.inflate(R.layout.map_fragment, container, false); //Se debe retornar un objeto view

        initMap();

        return root;
    }

    public void initMap() {

        //ESTE Codigo me daba nullpointer:  Posiblemente porque se llamaba antes de fragmentTransaction.commit()??
/*
        mSupportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance(); //?????
            //aqui hacer cosas con el fragmento supongo..?
            if (mSupportMapFragment != null) {
                googleMap = mSupportMapFragment.getMap();
                Log.d(TAG,"Hello from mSupportMapFragment null");
            }
            int test = googleMap.getMapType();
            Log.d(TAG,"INT test tag:" + test);
*/
        //  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 16));

        FragmentManager fManager= getChildFragmentManager();        //Se necesita un FragmentManager que gestione los fragmentos
        mSupportMapFragment = (SupportMapFragment) fManager         //Le decimos que este tipo de vista le pertenece
                .findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {                 //Forma de llamar al mapa en la nueva API
                if (map == null) {

                } else {                                            //Si tengo el mapa ya instanciado , procedo a llamar metodos para jugar con el mapa
                    googleMap = map;
                    googleMap.getUiSettings().setCompassEnabled(true);
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                    googleMap.getUiSettings().setRotateGesturesEnabled(true);
                    yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 12);
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));
                    googleMap.animateCamera(yourLocation);                  //Localiza el area de visión
                    googleMap.addMarker(new MarkerOptions().position(WTC).title("WTC Station 1"));
                }
            }
        });
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();

        fragmentTransaction.commit();       //El que permite que se muestre el fragmento

        FragmentTransaction ft = fManager.beginTransaction();
        fr1= new Fragment_footer();
        ft.add(R.id.top, fr1 , "top");
        fr2 = new Fragment_footer();
        ft.add(R.id.bottom, fr2, "bottom");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        fManager.executePendingTransactions();


    }


}


