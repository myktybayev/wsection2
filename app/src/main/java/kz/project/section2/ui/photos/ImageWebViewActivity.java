package kz.project.section2.ui.photos;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import kz.project.section2.R;

public class ImageWebViewActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_web_view);
        webView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        PhotoItem photoItem =(PhotoItem) intent.getSerializableExtra("photoItem");
        String photoName = photoItem.getImagePath().substring(11);//R.drawagle.image.png

        webView.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='"+photoName+"' style='width:100%' />", "text/html", "utf-8", null);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
    }

}