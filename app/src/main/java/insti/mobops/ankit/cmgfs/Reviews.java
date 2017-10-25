package insti.mobops.ankit.cmgfs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;


public class Reviews extends AppCompatActivity {

    String shopN ;
    private CustomerReview customerReview = new CustomerReview();
    private Eateries eateries = new Eateries();
    private NonEateries nonEateries = new NonEateries();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        Intent i = getIntent();
        shopN = i.getStringExtra("facy");
        int type = i.getIntExtra("type", 0);
        Log.v("main", shopN + type);
        packData();
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        if(type == 0){
        FragmentPagerAdapter adapterViewPager = new MyEateriesPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        }else{
            FragmentPagerAdapter adapterNonViewPager = new MyNonEateriesPagerAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapterNonViewPager);
        }

        vpPager.setPageTransformer(true, new RotateUpTransformer());
    }



    /*
   SEND DATA TO FRAGMENT (NOT FUNCTIONING)
    */
    private void packData() {
        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("FACY", shopN);
        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        customerReview.setArguments(bundle);
        eateries.setArguments(bundle);
        nonEateries.setArguments(bundle);
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
                    return customerReview;
                case 1:
                    return eateries;
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
                    return customerReview;
                case 1:
                    return nonEateries;
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
