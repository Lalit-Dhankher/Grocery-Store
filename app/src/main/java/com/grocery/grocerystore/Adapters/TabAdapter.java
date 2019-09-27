package com.grocery.grocerystore.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.grocery.grocerystore.HomeTab;
import com.grocery.grocerystore.OfferTab;

public class TabAdapter extends FragmentPagerAdapter {
    private Context myContext;
    private static final int TOTAL_TABS = 2;

    public TabAdapter(Context context,FragmentManager fm) {
        super(fm);
        myContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
        case 0:
        return HomeTab.newInstance("HomeTab, Instance 0");
        case 1:
        return OfferTab.newInstance("OfferTab, Instance 0");
        default:
        return HomeTab.newInstance("HomeTab, Instance 0");


        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Offers";
            default:
                return "";

        }
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
