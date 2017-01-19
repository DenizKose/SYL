package ru.greenslonik.deniz.syl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CatSongs extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WebView webView = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();

        String resName = "n" + intent.getIntExtra("head", 0);
        Log.i("name",resName);
        Context context = getBaseContext();

        String text = readRawTextFile(context, getResources().getIdentifier(resName, "raw", "ru.greenslonik.deniz.syl"));
        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);

    }



    public static String readRawTextFile(Context context, int resId){
        InputStream inputStream = context.getResources().openRawResource(resId);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while ((line = buffReader.readLine()) != null) {
                builder.append(line);
                builder.append("<br>");
            }
        } catch (IOException e){
            return null;
        }
        return builder.toString();
    }


}
