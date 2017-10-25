package com.example.ankit.cmgfs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;


public class reviews extends AppCompatActivity {

    String shopN ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        Intent i = getIntent();
        shopN = i.getStringExtra("facy");
        int type = i.getIntExtra("type", 0);
        Log.v("main", shopN + type);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        if(type == 1){
        FragmentPagerAdapter adapterViewPager = new MyEateriesPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        }
        if(type == 0){
            FragmentPagerAdapter adapterNonViewPager = new MyNonEateriesPagerAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapterNonViewPager);
        }

        vpPager.setPageTransformer(true, new RotateUpTransformer());
    }



    /*
   SEND DATA TO FRAGMENT (NOT FUNCTIONING)
    */
    private void sendData() {
        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("FACY", shopN);
        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        CustomerReview myFragment1 = new CustomerReview();
        myFragment1.setArguments(bundle);

    }

    public class MyEateriesPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 2;

        public MyEateriesPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    CustomerReview cus = new CustomerReview();
                    return cus;
                case 1:
                    return new eateries();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String title;
            if(position == 0) {
                title = "Customer Review";
            }else{
                title = "Shop Review";
            }
            return title;
        }

    }

    public class MyNonEateriesPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 2;

        public MyNonEateriesPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    CustomerReview cus = new CustomerReview();
                    return cus;
                case 1:
                    return new nonEateries();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String title;
            if(position == 0) {
                title = "Customer Review";
            }else{
                title = "Shop Review";
            }
            return title;
        }

    }

}
