package com.pixdart.drawerlayoutmaps.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pixdart.drawerlayoutmaps.R;

/**
 * Copyright (C) Drawer Layout And Maps Sample
 *
 * @author Gerónimo Oñativia <geronimox@gmail.com>
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = MapFragment.class.getSimpleName();
    private MapView mapView;
    private GoogleMap googleMap;

    public static MapFragment newInstance() {
        Log.d(TAG, "Instancing...");
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        //Do stuff with the map, as its now available
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //DOCS: https://developers.google.com/maps/documentation/android/views
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-26.819573, -65.258375), 15), 1000, null);
    }

    public void clearMarkers() {
        if (googleMap != null) googleMap.clear();
    }

    // DOCS: https://developers.google.com/maps/documentation/android/marker
    public void addMarker(double lat, double lon, String title) {
        if (googleMap != null)
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title(title));
    }

    public void animateTo(double lat, double lon, int time) {
        if (googleMap != null)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 17), time, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
