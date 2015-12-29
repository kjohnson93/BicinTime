package app.bicintime.wolf.navdrawer;

/**
 * Created by wolf on 12/23/2015.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;




public class MapFragmentUnused extends SupportMapFragment implements GoogleMap.OnMarkerClickListener {

    LatLng myCoordinates = new LatLng(41.387128, 2.168565);
    final LatLng WTC = new LatLng(41.372203, 2.180496);       //Coordenadas de la estación bicing de mi trabajo
    GoogleMap googleMap;                                       //objeto de tipo googleMap que es el mapa que voy a mostrar

    SupportMapFragment mSupportMapFragment;                     //Fragmento que envolvera al googleMap
    CameraUpdate yourLocation;                                  //Para colocar el area de visión

    Fragment_footer fr1,fr2;

    TextView tw;


    //El constructor normal de un objeto que extiende SupportMapFragment no deja crear un objeto es porque no deja pasarle argumentos mientras se construye porque deben tener un constructor vacio por defecto
    //pass the arguments to the fragment while constructing it aswel: static method. REASON why we not pass arguments to construcot its because they must have a empty constructor by default
    public static MapFragmentUnused newInstance()                 //Con un constructor metodo static si que se puede pasar argumentos
    {
        MapFragmentUnused frag = new MapFragmentUnused();


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

                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {  //HE DE HACER AQUI TODO LO RELACIONADO CON googleMap, sino me da error nllpointer
                        @Override                                                               //desconozco porque, supongo que por la función de getMapAsyncs, procesos de Android, etc...
                        public boolean onMarkerClick(Marker marker) {

                            Log.d("BICIN", "ESTOY ENTRANDO 2");
                            //fragmentManager = getSupportFragmentManager(); //me fallaba esta línia, ... utilizaba creo getFragmentManager directo, porque fallaba???


                            Log.d("BICIN", "ESTOY ENTRANDO 2");
                            Log.d("BICIN", "ESTOY ENTRANDO 2");


                            tw = (TextView) getView().findViewById(R.id.textFoot);              //No hace falta, hacer lo que hace slidenerd, ya que de momento estoy ocultando una parte del
                                                                                                //XML, no un fragmento entero, pero me pregunto si esto produce type coupling???
                            tw.setVisibility(View.GONE);

                            return false;
                        }
                    });
                }
            }
        });
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();                  //ME PREGUNTO, si esta forma es la más correcta, seguro que no
                                                                                                //Sirve para añadir los dos fragmentos footer
        fragmentTransaction.commit();       //El que permite que se muestre el fragmento

        FragmentTransaction ft = fManager.beginTransaction();
        fr1= new Fragment_footer();
        ft.add(R.id.top, fr1 , "top");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        fManager.executePendingTransactions();




    }

    public void MarkerClicked(){                                    //este método seguro que nunca la utilizaré, lo creé pensando en la forma de slidenerd de comunicar los fragmentos.

        tw = (TextView) getView().findViewById(R.id.textFoot);

        tw.setVisibility(View.GONE);




    }


    @Override
    public boolean onMarkerClick(Marker marker) {  //TENGO que implementar este método obligatoriamente por la herencia, pero no lo utilizo nunca.
        return false;                               //debido al listener de arriba, hay alguna forma de omitir esto?? quien sabe
    }
}


