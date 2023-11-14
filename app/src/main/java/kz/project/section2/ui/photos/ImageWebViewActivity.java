package kz.project.section2.ui.photos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.webkit.WebView;
import android.widget.ImageView;

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

//        webView.loadUrl("https://vk.com/video-23053131_456241034");
    }

}