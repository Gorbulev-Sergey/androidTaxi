package ru.gorbulevsv.androidtaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class DriverMapsActivity extends AppCompatActivity {
    Toolbar toolbar;
    private MapView mapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey("0113c475-ed22-4eb7-8bf3-2ab5d3174938");
        MapKitFactory.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_maps);

        toolbar = findViewById(R.id.toolbarDriverMaps);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        mapview = findViewById(R.id.driverMaps);
        mapview.getMap().move(
                new CameraPosition(new Point(54.516011, 36.246966), 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}