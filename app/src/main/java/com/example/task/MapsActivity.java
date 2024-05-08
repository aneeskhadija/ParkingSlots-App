package com.example.task;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.task.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ArrayList<MapsModel> rowsArrayList = new ArrayList<>();
    //private String url  = "http://park.trulypristine.com/api/Parking/PARKLIST";
    private String url  = "http://rafiullah0318-001-site1.itempurl.com/api/Parking/PARKLIST";

    private ArrayList<LatLng> latlngs = new ArrayList<>();
    double lat;
    double lon;
    LatLng latLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {

        mMap = googleMap;
        rowsArrayList.clear();
        createMarkersFromJson();

        enableMyLocation();
    }


    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }


    private void createMarkersFromJson() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    //  hideProgressBar();
                    Log.d("JSON_Response", "onResponse: " + response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray mapsData = jsonObject.getJSONArray("ParkList");

                        try {
                            for (int s = 0; s<mapsData.length(); s++){
                                JSONObject object = mapsData.getJSONObject(s);
                                Log.d("allObjects", "onResponse" + object);
                                double longitude = object.getDouble("PARK_LONGITUDE");
                                double latitude = object.getDouble("PARK_LATITUDE");
                                String clientId = object.getString("CLIENT_ID");
                                String parkName = object.getString("PARK_NAME");
                                String contactNumber = object.getString("CONTACT_NUMBER");

                                latlngs.clear();
                                latlngs.add(new LatLng(latitude, longitude));
                                MapsModel model = new MapsModel();
                                model.setClientId(clientId);
                                model.setContact(contactNumber);
                                model.setParkName(parkName);
                                model.setLatitude(latitude);
                                model.setLongitude(longitude);
                                rowsArrayList.add(model);

                                for(LatLng location : latlngs){
                                    mMap.addMarker(new MarkerOptions()
                                            .position(location)
                                            .title(parkName));
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

                                    mMap.setOnMarkerClickListener(marker -> {
                                       Toast.makeText(this, ""+marker.getTitle(), Toast.LENGTH_SHORT).show();

                                        int searchListLength = rowsArrayList.size();
                                        if (searchListLength == 0){
                                            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
                                        }else {
                                            for(MapsModel myPoint : rowsArrayList) {
                                                if(myPoint.getParkName() != null && myPoint.getParkName().equals(marker.getTitle())) {
    //                                               Toast.makeText(this, myPoint.getParkName()+ "  "+ marker.getTitle(), Toast.LENGTH_SHORT).show();

                                                    lat = myPoint.getLatitude();
                                                    lon = myPoint.getLongitude();
                                                    latLng = new LatLng(lat, lon);
                                                   // mMap.addMarker(new MarkerOptions().position(latLng).title("Car Parking Here"));
                                                   // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f));
                                                    Intent intent = new Intent(MapsActivity.this, SecondActivity.class);
                                                    intent.putExtra("lat", String.valueOf(myPoint.getLatitude()));
                                                    intent.putExtra("lng", String.valueOf(myPoint.getLongitude()));
                                                    intent.putExtra("country", myPoint.getParkName());
                                                    intent.putExtra("contact", myPoint.getContact());
                                                    startActivity(intent);
                                                    mMap.addMarker(new MarkerOptions().position(latLng).title("Car Parking Here"));
                                                     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f));
                                                }
                                            }
                                        }
                                        return true;
                                    });

                                }

                                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
    //                            String cityName = addresses.get(0).getAddressLine(0);
    //                            String stateName = addresses.get(0).getAddressLine(1);
    //                            String countryName = addresses.get(0).getAddressLine(2);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("jassonValue", e.toString());
                    }
                },
                error -> {
                    Log.d("JSON_Response", "onErrorResponse: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("PARK_NAME","");
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}