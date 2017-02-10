package ru.greenslonik.deniz.syl.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.greenslonik.deniz.syl.CheckInternet;
import ru.greenslonik.deniz.syl.R;

public class MainScreen extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.mainscreen_layout,null);

        WebView wv_gif = (WebView) v.findViewById(R.id.wv_gif);
        wv_gif.getSettings().setAppCacheMaxSize(5*1024*1024);
        wv_gif.getSettings().setAppCachePath("/cache/");
        wv_gif.getSettings().setAllowFileAccess(true);
        wv_gif.getSettings().setAppCacheEnabled(true);
        wv_gif.getSettings().setJavaScriptEnabled(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (CheckInternet.checkConnection(getContext())) {
                wv_gif.loadUrl("https://m.vk.com/sinegoria?own=0#wall");

            } else {
                wv_gif.loadUrl("file:///android_asset/warning.html");
            }
        }

        return v;
    }

}
