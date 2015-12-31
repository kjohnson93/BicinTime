package app.bicintime.wolf.navdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * Created by wolf on 12/27/2015.
 */
public class MapFragmentA extends Fragment implements GoogleMap.OnMarkerClickListener {


    MapView mMapView;
    private GoogleMap googleMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.map_fragment_a_layout, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        googleMap = mMapView.getMap();
        // latitude and longitude
        double latitude = 41.372203;
        double longitude = 2.180496;

        final LatLng WTC = new LatLng(41.372203, 2.180496);

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);



        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {  //HE DE HACER AQUI TODO LO RELACIONADO CON googleMap, sino me da error nllpointer
            @Override
            //desconozco porque, supongo que por la función de getMapAsyncs, procesos de Android, etc...
            public boolean onMarkerClick(Marker marker) {

                Log.d("BICIN", "ESTOY ENTRANDO 2");
                //fragmentManager = getSupportFragmentManager(); //me fallaba esta línia, ... utilizaba creo getFragmentManager directo, porque fallaba???


                Log.d("BICIN", "ESTOY ENTRANDO 2");
                Log.d("BICIN", "ESTOY ENTRANDO 2");


                //TextView tw = (TextView) getView().findViewById(R.id.textFootA);              //No hace falta, hacer lo que hace slidenerd, ya que de momento estoy ocultando una parte del
                //XML, no un fragmento entero, pero me pregunto si esto produce type coupling???
                // tw.setVisibility(View.GONE);
                Button button1 = (Button) getView().findViewById(R.id.buttonFootAclick2);
                button1.setVisibility(View.GONE);

                //Button button2 = (Button) getView().findViewById(R.id.fragment_linearlayout_2_button1);
                LinearLayout linearLayout2 = (LinearLayout) getView().findViewById(R.id.fragment_linearlayout_2);
                linearLayout2.setVisibility(View.GONE);

                return false;
            }
        });


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(41.372203, 2.180496)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));






        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TabFragment tabFragment = new TabFragment();

        Button button2 = (Button) getView().findViewById(R.id.buttonFootNULL);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TabFragment tabFragment = new TabFragment();

                tabFragment.viewPager.setCurrentItem(1); //Esto funciona, dunno why... supongo que tendre errores cuanto quiera pasar info de una tab a otra.

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {



        return false;
    }
}
