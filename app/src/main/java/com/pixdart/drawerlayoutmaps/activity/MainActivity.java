package com.pixdart.drawerlayoutmaps.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pixdart.drawerlayoutmaps.R;
import com.pixdart.drawerlayoutmaps.fragment.InfoFragment;
import com.pixdart.drawerlayoutmaps.fragment.MapFragment;

/**
 * Copyright (C) Drawer Layout And Maps Sample
 *
 * @author Gerónimo Oñativia <geronimox@gmail.com>
 */
public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String[] drawerItems;
    private CharSequence title;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerItems = getResources().getStringArray(R.array.drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, R.id.text, drawerItems));
        mDrawerList.setOnItemClickListener(this);

        if (savedInstanceState != null) {
            mapFragment = (MapFragment) getSupportFragmentManager().findFragmentByTag(MapFragment.class.getSimpleName());
        } else {
            selectItem(0);
        }
    }

    private void selectItem(int position) {
        mDrawerLayout.closeDrawer(mDrawerList);
        switch (position) {
            case 0://Mapa
                if (mapFragment == null) mapFragment = MapFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mapFragment, MapFragment.class.getSimpleName()).commit();
                setTitle(drawerItems[position]);
                break;
            case 1://Add Home Marker
                if (mapFragment != null) {
                    Toast.makeText(this, drawerItems[position], Toast.LENGTH_SHORT).show();
                    mapFragment.clearMarkers();
                    mapFragment.addMarker(-26.819573, -65.258375, "Home");
                } else {
                    Log.wtf(TAG, "Map Fragment not available!");
                }
                break;
            case 2://Animate to Home Marker
                if (mapFragment != null) {
                    Toast.makeText(this, drawerItems[position], Toast.LENGTH_SHORT).show();
                    mapFragment.animateTo(-26.819573, -65.258375, 1500);
                } else {
                    Log.wtf(TAG, "Map Fragment not available!");
                }
                break;
            case 3://Informacion
                InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentByTag(InfoFragment.class.getSimpleName());
                if (infoFragment == null) infoFragment = InfoFragment.newInstance("NEW FRAGMENT REPLACING THE MAP");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, infoFragment, InfoFragment.class.getSimpleName()).commit();
                setTitle(drawerItems[position]);
                break;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        //noinspection ConstantConditions
        getSupportActionBar().setTitle(this.title);
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);
    }

}
