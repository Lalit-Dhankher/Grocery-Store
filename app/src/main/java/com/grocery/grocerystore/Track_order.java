package com.grocery.grocerystore;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.grocery.grocerystore.Adapters.TrackOrderAdapter;
import com.grocery.grocerystore.Data.TrackOrderData;

import java.util.List;

public class Track_order extends Activity {

    RecyclerView rvTrackOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        rvTrackOrder=findViewById(R.id.rvTrackOrder);

        AndroidNetworking.initialize(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvTrackOrder.setLayoutManager(gridLayoutManager);


        AndroidNetworking.get("https://grocery-store-250011.firebaseio.com/grocery_store/track_order.json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(TrackOrderData.class, new ParsedRequestListener<List<TrackOrderData>>() {
                    @Override
                    public void onResponse(List<TrackOrderData> trackOrderData) {
                        TrackOrderAdapter trackOrderAdapter = new TrackOrderAdapter(getBaseContext(), trackOrderData);
                        rvTrackOrder.setAdapter(trackOrderAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });






    }
}
