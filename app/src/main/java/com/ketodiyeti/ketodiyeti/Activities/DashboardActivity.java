package com.ketodiyeti.ketodiyeti.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ketodiyeti.ketodiyeti.Classes.Person;
import com.ketodiyeti.ketodiyeti.R;
import com.ketodiyeti.ketodiyeti.adapter.ViewPagerAdapter;
import com.ketodiyeti.ketodiyeti.fragments.AksamFragment;
import com.ketodiyeti.ketodiyeti.fragments.AraOgunFragment;
import com.ketodiyeti.ketodiyeti.fragments.KahvaltiFragment;
import com.ketodiyeti.ketodiyeti.fragments.OgleFragment;
import com.ketodiyeti.ketodiyeti.fragments.TotalFragment;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener,
        TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    Person person;
    Bundle extras;
    ImageView photo_imgview;
    TextView txt_personName, txt_personEmail;

    private SwipeRefreshLayout swipeRefreshLayout = null;
    private TabLayout tabLayout = null;
    private ViewPager vpFragments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        extras = getIntent().getExtras();
        person = new Person(extras.getString("name"), extras.getString("mail"), extras.getString("yas"), extras.getString("kilo"), extras.getString("boy"), extras.getString("cins"),
                extras.getString("id"), extras.getString("foto"));
        init();
        initView();

    }

    public void init() {
        photo_imgview = findViewById(R.id.imageView);
        txt_personName = findViewById(R.id.personname_txt);
        txt_personEmail = findViewById(R.id.personemail_txt);
        swipeRefreshLayout = findViewById(R.id.activity_view_pager_sample_swipeRefreshLayout);
        tabLayout = findViewById(R.id.activity_view_pager_sample_tablayout);
        vpFragments = findViewById(R.id.activity_view_pager_sample_vpFragments);
    }

    public void initView() {
        Picasso.with(DashboardActivity.this).load(person.getFotoUrl()).into(photo_imgview);
        photo_imgview.setImageURI(Uri.parse(person.getFotoUrl()));
        txt_personName.setText(person.getIsim());
        txt_personEmail.setText(person.getMail());
        swipeRefreshLayout.setOnRefreshListener(this);

        tabLayout.setupWithViewPager(vpFragments);
        tabLayout.addOnTabSelectedListener(this);

        ViewPagerAdapter viewpagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        TotalFragment totalFragment = new TotalFragment();
        KahvaltiFragment kahvaltiFragment = new KahvaltiFragment();
        OgleFragment ogleFragment = new OgleFragment();
        AksamFragment aksamFragment = new AksamFragment();
        AraOgunFragment araOgunFragment = new AraOgunFragment();

        viewpagerAdapter.addFragment(totalFragment, "Toplam");
        viewpagerAdapter.addFragment(kahvaltiFragment, "Kahvaltı");
        viewpagerAdapter.addFragment(ogleFragment, "Öğle");
        viewpagerAdapter.addFragment(araOgunFragment, "Ara Öğün");
        viewpagerAdapter.addFragment(aksamFragment, "Akşam");

        vpFragments.setAdapter(viewpagerAdapter);
        vpFragments.addOnPageChangeListener(this);
        vpFragments.setCurrentItem(0);
    }

    @Override
    public void onRefresh() {


        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                Toast.makeText(DashboardActivity.this, "Kalan Süre" + millisUntilFinished, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                swipeRefreshLayout.setRefreshing(false);
            }

        }.start();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        Toast.makeText(this, "İlk kez seçilen tab " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

        Toast.makeText(this, "Seçimi kaldırılan tab : " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

        Toast.makeText(this, "Yeniden Seçilen Tab" + tab.getText(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_prof) {
            // Handle the camera action
        } else if (id == R.id.nav_yemek) {

        } else if (id == R.id.nav_arki) {

        } else if (id == R.id.nav_keto) {

        } else if (id == R.id.nav_ketoPlan) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Toast.makeText(this, "Fragment Scrolled", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "Fragment Selected", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Toast.makeText(this, "Fragment onPageScrollStateChanged", Toast.LENGTH_SHORT).show();
    }
}
