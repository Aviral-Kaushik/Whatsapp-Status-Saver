package com.aviral.whatsappstatussaver;

import static com.aviral.whatsappstatussaver.Utils.PermissionsManager.checkAndVerifyPermission;
import static com.aviral.whatsappstatussaver.Utils.PermissionsManager.requestPermission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aviral.whatsappstatussaver.Adapter.SectionPagerAdapter;
import com.aviral.whatsappstatussaver.Utils.PermissionsManager;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkAndVerifyPermission(PermissionsManager.PERMISSIONS, this)) {
            requestPermission(PermissionsManager.PERMISSIONS, this);
        }

        viewPager = findViewById(R.id.viewpager_container);

        setUpViewPager();
    }

    private void setUpViewPager() {

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ImagesFragment());
        adapter.addFragment(new VideosFragment());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("Images");
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText("Videos");

    }
}