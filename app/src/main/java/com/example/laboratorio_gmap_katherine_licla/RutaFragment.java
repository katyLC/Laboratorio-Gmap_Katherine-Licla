package com.example.laboratorio_gmap_katherine_licla;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class RutaFragment extends Fragment {


    private EditText tvHasta;
    private EditText tvDesde;
    private Button btRuta;
    private MapView mapRuta;
    private GoogleMap mMap;

    public RutaFragment() {
        // Required empty public constructor
    }

    public static RutaFragment newInstance() {
        RutaFragment fragment = new RutaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ruta, container, false);
        initView(view);

        mapRuta = view.findViewById(R.id.mapRuta);
        mapRuta.onCreate(savedInstanceState);
        mapRuta.onResume();
        mapRuta.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
            }
        });
        return view;
    }

    private void initView(View view) {
        tvHasta = view.findViewById(R.id.tv_hasta);
        tvDesde = view.findViewById(R.id.tv_desde);
        btRuta = view.findViewById(R.id.bt_ruta);
        mapRuta = view.findViewById(R.id.mapRuta);

        btRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(tvDesde.getText().toString().trim())) {
                    Toast.makeText(getContext(), "Ingresar coordenadas inciales", Toast.LENGTH_LONG).show();
                } else if ("".equals(tvHasta.getText().toString().trim())) {
                    Toast.makeText(getContext(), "Ingresar coordenadas finales", Toast.LENGTH_LONG).show();
                } else {
                    new RutaMapa(getContext(), mMap, tvDesde.getText().toString(), tvHasta.getText().toString()).execute();
                }
            }
        });
    }

    private void setUpMapIfNeeded() {
        setUpMap();
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
