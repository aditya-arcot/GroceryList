package com.example.grocerylist

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.grocerylist.databinding.ActivityNearbyGroceryStoresBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions


class NearbyGroceryStoresActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityNearbyGroceryStoresBinding
    private val TAG = NearbyGroceryStoresActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNearbyGroceryStoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setMapStyle(map)

        //UT coordinates
        val lat = 30.286229970772535
        val long = -97.7393836892685
        val latLng = LatLng(lat, long)

        val zoomLevel = 13f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))

        populateNearbyGroceryStores(map)

        map.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    private fun populateNearbyGroceryStores(map:GoogleMap) {
        map.addMarker(MarkerOptions().position(LatLng(30.300281, -97.720095)).title("H-E-B"))
        map.addMarker(MarkerOptions().position(LatLng(30.297489, -97.741279)).title("Wheatsville Co-Op"))
        map.addMarker(MarkerOptions().position(LatLng(30.295891, -97.743687)).title("Rio Market"))
        map.addMarker(MarkerOptions().position(LatLng(30.304918, -97.726669)).title("Fresh Plus"))
        map.addMarker(MarkerOptions().position(LatLng(30.280965, -97.758638)).title("Fresh Plus"))
        map.addMarker(MarkerOptions().position(LatLng(30.304045, -97.764297)).title("Randalls"))
        map.addMarker(MarkerOptions().position(LatLng(30.285156, -97.746545)).title("West Campus Market"))
        map.addMarker(MarkerOptions().position(LatLng(30.288229, -97.747763)).title("Orange Market"))
        map.addMarker(MarkerOptions().position(LatLng(30.283481, -97.741924)).title("Target"))
        map.addMarker(MarkerOptions().position(LatLng(30.284039, -97.745006)).title("Rio Mart"))
        map.addMarker(MarkerOptions().position(LatLng(30.271471, -97.753937)).title("Whole Foods Market"))
        map.addMarker(MarkerOptions().position(LatLng(30.267945, -97.752715)).title("Trader Joe’s"))
        map.addMarker(MarkerOptions().position(LatLng(30.267846, -97.749427)).title("Royal Blue"))
        map.addMarker(MarkerOptions().position(LatLng(30.267846, -97.749427)).title("Mini Mart"))
    }


    private fun setMapStyle(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )

            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.normal_map -> {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.satellite_map -> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}