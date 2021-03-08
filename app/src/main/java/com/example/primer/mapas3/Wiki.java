package com.example.primer.mapas3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Wiki extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);
        WebView Wv = findViewById(R.id.webView);
        getIntent().getExtras().getString("ciudad");
        String url = "https://en.wikipedia.org/wiki/";
        Wv.loadUrl(url);
    }
}