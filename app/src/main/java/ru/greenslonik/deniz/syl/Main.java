package ru.greenslonik.deniz.syl;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;


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


    private String[] scope = new String[] {VKScope.FRIENDS,VKScope.STATUS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //ListView news_feed = (ListView) findViewById(R.id.news_feed);

        VKSdk.initialize(getApplicationContext());




        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater.start();
        new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://raw.githubusercontent.com/DenizKose/SYL/master/update/update.xml")
                .start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Сасай :)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
    }

    //TODO: VK WALL
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from("group_ids","sinegoria"));
                vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        VKList  vkList = (VKList) response.parsedModel;

                        VKRequest vkRequest1 = null;
                        try {
                            vkRequest1 = new VKApiWall()
                                    .get(VKParameters.from(VKApiConst.OWNER_ID, "-"+vkList.get(0).fields.getInt("id"),  VKApiConst.COUNT, 100 ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        vkRequest1.executeWithListener(new VKRequest.VKRequestListener() {
                            @Override
                            public void onComplete(VKResponse response) {
                                super.onComplete(response);
                                System.out.print(response.responseString);
                            }
                        });

                    }
                });

                // Пользователь успешно авторизовался
                Toast.makeText(getApplicationContext(),"Вы авторизованы!",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast.makeText(getApplicationContext(),"Упс, не получилось :(",Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

            ftrans.replace(R.id.container, frlessons);

        } else if (id == R.id.nav_manage) {

            Toast.makeText(getApplicationContext(),"Нечего насроить :)",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            AppUpdater appUpdater = new AppUpdater(this);
            appUpdater.start();
            new AppUpdater(this)
                    .setUpdateFrom(UpdateFrom.XML)
                    .setUpdateXML("https://raw.githubusercontent.com/DenizKose/SYL/master/update/update.xml")
                    .start();

        }  else if (id == R.id.nav_share) {

        }  else if (id == R.id.nav_update) {
            AppUpdater appUpdater = new AppUpdater(this);
            appUpdater.start();
            new AppUpdater(this)
                    .setUpdateFrom(UpdateFrom.XML)
                    .setUpdateXML("https://raw.githubusercontent.com/DenizKose/SYL/master/update/update.xml")
                    .start();

        }  else if (id == R.id.nav_login_vk) {
            //VKSdk.login(this, scope);
            Toast.makeText(getApplicationContext(),"Скоро",Toast.LENGTH_SHORT).show();
        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
