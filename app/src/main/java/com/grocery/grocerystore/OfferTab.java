package com.grocery.grocerystore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.grocery.grocerystore.Adapters.OfferTabAdapter;
import com.grocery.grocerystore.Data.OfferTabData;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class OfferTab extends Fragment {

    RecyclerView rvOfferTab,rvOfferTabInner;
    LinearLayout llOfferTabInner;
    ImageView ivOfferTabInner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);

        rvOfferTab = view.findViewById(R.id.rvOfferTab);
        rvOfferTabInner=view.findViewById(R.id.rvOfferTabInner);
        llOfferTabInner=view.findViewById(R.id.llOfferTabInner);
        ivOfferTabInner=view.findViewById(R.id.ivOfferTabInner);
        AndroidNetworking.initialize(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvOfferTab.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvOfferTabInner.setLayoutManager(linearLayoutManager1);


        AndroidNetworking.get("https://grocery-store-250011.firebaseio.com/grocery_store/offer_tab.json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(OfferTabData.class, new ParsedRequestListener<List<OfferTabData>>() {
                    @Override
                    public void onResponse(List<OfferTabData> offerTabData) {
                        OfferTabAdapter offerTabAdapter = new OfferTabAdapter(getContext(), offerTabData,rvOfferTab,rvOfferTabInner,llOfferTabInner,ivOfferTabInner);
                        rvOfferTab.setAdapter(offerTabAdapter);


                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });


        //post evenrt

        EventBus.getDefault().post(new ViewEvent(rvOfferTab,rvOfferTabInner,llOfferTabInner));



        return view;


    }



    public static OfferTab newInstance(String text) {
        OfferTab offerTab = new OfferTab();
        Bundle b = new Bundle();
        b.putString("msg", text);

        offerTab.setArguments(b);
        return offerTab;
    }



    //// define event bus

    public static class ViewEvent{
        RecyclerView rvOfferTab,rvOfferTabInner;
        LinearLayout llOfferTabInner;


        public ViewEvent(RecyclerView rvOfferTab,RecyclerView rvOfferTabInner, LinearLayout llOfferTabInner) {
            this.rvOfferTab = rvOfferTab;
            this.rvOfferTabInner = rvOfferTabInner;
            this.llOfferTabInner = llOfferTabInner;
        }

        public RecyclerView getRvOfferTab() {
            return rvOfferTab;
        }

        public RecyclerView getRvOfferTabInner() {
            return rvOfferTabInner;
        }

        public LinearLayout getLlOfferTabInner() {
            return llOfferTabInner;
        }
    }



}