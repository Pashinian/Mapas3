package com.example.primer.mapas3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Mapa2 extends FragmentActivity implements OnMapReadyCallback, ClasePeticion.IRellenarSpinner{
    private GoogleMap mapa;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
    }

    @Override
    public void rellenarSpinner(List<Ciudad> ciudades) {
    //No rellenamos spinner, se ponen marcadores.
        for (Ciudad c: ciudades
             ) {
            Double latitud = Double.parseDouble(c.getLat());
            Double longitud = Double.parseDouble(c.getLng());
            LatLng posicion = new LatLng(latitud, longitud);
            MarkerOptions opciones = new MarkerOptions().position(posicion).title(c.getCity()+" "+c.getPopulation());
            Marker marcador = mapa.addMarker(opciones);
            marcador.setTag(c);

        }
        GoogleMap.OnMarkerClickListener oyente_marcador = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Ciudad clicada = (Ciudad)marker.getTag();
                Log.d("Clicada", clicada.toString());
                Intent i = new Intent(context, Wiki.class);
                i.putExtra("ciudad", clicada.getCity());
                startActivity(i);

                return false;
            }
        };
        mapa.setOnMarkerClickListener(oyente_marcador);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    mapa = googleMap;
    ClasePeticion.pedirJSON(this);
    }
}
