package ru.greenslonik.deniz.syl;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.greenslonik.deniz.syl.fragments.About;
import ru.greenslonik.deniz.syl.fragments.Games1;
import ru.greenslonik.deniz.syl.fragments.Games10;
import ru.greenslonik.deniz.syl.fragments.Games2;
import ru.greenslonik.deniz.syl.fragments.Games3;
import ru.greenslonik.deniz.syl.fragments.Games4;
import ru.greenslonik.deniz.syl.fragments.Games5;
import ru.greenslonik.deniz.syl.fragments.Games6;
import ru.greenslonik.deniz.syl.fragments.Games7;
import ru.greenslonik.deniz.syl.fragments.Games8;
import ru.greenslonik.deniz.syl.fragments.Games9;
import ru.greenslonik.deniz.syl.fragments.Lessons;
import ru.greenslonik.deniz.syl.fragments.MainScreen;
import ru.greenslonik.deniz.syl.fragments.Songs;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Games1 frgames1;
    Games2 frgames2;
    Games3 frgames3;
    Games4 frgames4;
    Games5 frgames5;
    Games6 frgames6;
    Games7 frgames7;
    Games8 frgames8;
    Games9 frgames9;
    Games10 frgames10;
    Songs frsongs;
    Lessons frlessons;
    About frabout;
    MainScreen frmainscreen;


    private String[] scope = new String[] {VKScope.FRIENDS,VKScope.STATUS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final View view = findViewById(R.id.snack_view);
        Snackbar.make(view,R.string.help_text,Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Кнопка тут >",Toast.LENGTH_SHORT).show();
            }
        }).show();

       // ListView news_feed = (ListView) findViewById(R.id.news_feed);

        VKSdk.initialize(getApplicationContext());




        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater.start();
        new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://raw.githubusercontent.com/DenizKose/SYL/master/update/update.xml")
                .start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);


            }
        });


        frgames1  = new Games1();
        frgames2  = new Games2();
        frgames3  = new Games3();
        frgames4  = new Games4();
        frgames5  = new Games5();
        frgames6  = new Games6();
        frgames7  = new Games7();
        frgames8  = new Games8();
        frgames9  = new Games9();
        frgames10  = new Games10();
        frsongs  = new Songs();
        frlessons  = new Lessons();
        frabout = new About();
        frmainscreen = new MainScreen();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.container, frmainscreen);
        ftrans.commit();
    }

    protected void sendEmail() {

        String[] TO = {"deniz8011@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Отправить Email"));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Main.this,
                    "Не установлено ни одного почтового клиента", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_songs) {

            ftrans.replace(R.id.container, frsongs);

        } else if (id == R.id.nav_games1) {

            ftrans.replace(R.id.container, frgames1);

        } else if (id == R.id.nav_games2) {

            ftrans.replace(R.id.container, frgames2);

        } else if (id == R.id.nav_games3) {

            ftrans.replace(R.id.container, frgames3);

        } else if (id == R.id.nav_games4) {

            ftrans.replace(R.id.container, frgames4);

        } else if (id == R.id.nav_games5) {

            ftrans.replace(R.id.container, frgames5);

        } else if (id == R.id.nav_games6) {

            ftrans.replace(R.id.container, frgames6);

        } else if (id == R.id.nav_games7) {

            ftrans.replace(R.id.container, frgames7);

        } else if (id == R.id.nav_games8) {

            ftrans.replace(R.id.container, frgames8);

        } else if (id == R.id.nav_games9) {

            ftrans.replace(R.id.container, frgames9);

        } else if (id == R.id.nav_games10) {

            ftrans.replace(R.id.container, frgames10);

        } else if (id == R.id.nav_lessons) {
            Toast.makeText(getApplicationContext(),"Потерпи дружок ;)",Toast.LENGTH_SHORT).show();
           // ftrans.replace(R.id.container, frlessons);

        } else if (id == R.id.nav_manage) {

            Toast.makeText(getApplicationContext(),"Нечего настроить :)",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_about) {

            ftrans.replace(R.id.container, frabout);

        } else if (id == R.id.nav_group_vk) {
            Uri vk = Uri.parse("https://vk.com/sinegoria");
            Intent intent = new Intent(Intent.ACTION_VIEW, vk);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            sendEmail();

        }  else if (id == R.id.nav_update) {
            AppUpdater appUpdater = new AppUpdater(this);
            appUpdater.start();
            new AppUpdater(this)
                    .setUpdateFrom(UpdateFrom.XML)
                    .setUpdateXML("https://raw.githubusercontent.com/DenizKose/SYL/master/update/update.xml")
                    .start();

            Toast.makeText(getApplicationContext(),"Ищу обновления...",Toast.LENGTH_SHORT).show();

        }  else if (id == R.id.nav_login_vk) {
           // VKSdk.login(this, scope);
            Toast.makeText(getApplicationContext(),"Ахахаха, не сейчас :D",Toast.LENGTH_SHORT).show();
        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
