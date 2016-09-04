package alexandria.kris.logisticapp;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.Map;

import alexandria.kris.logisticapp.model.DirectionModel;
import alexandria.kris.logisticapp.model.Leg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Map<String, String> parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        parameters = new HashMap<>();
        // Add a marker in Sydney and move the camera
        fetchDistance("", "");
/*        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                fetchDistance("", "");
            }
        });*/
    }

    private void fetchDistance(String latitude, String longitude) {
        GoogleApiInterface apiService = GoogleApiClient.getClient().create(GoogleApiInterface.class);
        parameters.clear();
        parameters.put("origin", "Brooklyn");
        parameters.put("destination", "Queens");
        parameters.put("mode", "bicycling");
        parameters.put("key", "AIzaSyDhAonRJef0uzBDxcznv9gyi5TT33Aei6M");
        Call<DirectionModel> call = apiService.getDistance(parameters);
        call.enqueue(new Callback<DirectionModel>() {
            @Override
            public void onResponse(Call<DirectionModel> call, Response<DirectionModel> response) {
                Leg leg =response.body().getRoutes().get(0).getLegs().get(0);
                LatLng startLatLng = new LatLng(leg.getStartLocation().getLat(), leg.getStartLocation().getLng());
                LatLng endLatLng = new LatLng(leg.getEndLocation().getLat(), leg.getEndLocation().getLng());

                mMap.addMarker(new MarkerOptions().position(startLatLng));
                mMap.addMarker(new MarkerOptions().position(endLatLng));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(startLatLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                for (int i =0; i < leg.getSteps().size(); i++) {
                    LatLng polygonStartLatLng = new LatLng(leg.getSteps().get(i).getStartLocation().getLat(),
                            leg.getSteps().get(i).getStartLocation().getLng());

                    LatLng polygonEndLatLng = new LatLng(leg.getSteps().get(i).getEndLocation().getLat(),
                            leg.getSteps().get(i).getEndLocation().getLng());

                    PolylineOptions polylineOptions = new PolylineOptions()
                            .add(polygonStartLatLng, polygonEndLatLng)
                            .width(5)
                            .color(Color.GREEN);

                    mMap.addPolyline(polylineOptions);
                }
            }

            @Override
            public void onFailure(Call<DirectionModel> call, Throwable t) {

            }
        });
    }
}
