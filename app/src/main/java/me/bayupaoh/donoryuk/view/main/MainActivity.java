package me.bayupaoh.donoryuk.view.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.view.main.jadwaldonor.JadwalDonorFragment;
import me.bayupaoh.donoryuk.view.main.stockdarah.StockDarahFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tabs)
    TabLayout tabLayout;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.main_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    int mutedColor = R.attr.colorPrimary;

    private Unbinder unbinder;

    private int[] tabIcons = {
            R.drawable.ic_blood,
            R.drawable.ic_place_event
    };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        setupToolbar();
        setupViewPager();
        setupTab();
        setupTabIcons();
    }

    private void setupViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new StockDarahFragment(),"Stok Darah");
        viewPagerAdapter.addFrag(new JadwalDonorFragment(),"Jadwal Donor");
        viewPager.setAdapter(viewPagerAdapter);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
    private void setupTab() {
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupSubTitle(String tittle){
        getSupportActionBar().setSubtitle(tittle);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donor Yuk!");
        getSupportActionBar().setSubtitle("Jawa Barat");

        collapsingToolbarLayout.setTitleEnabled(false);
    }
}
