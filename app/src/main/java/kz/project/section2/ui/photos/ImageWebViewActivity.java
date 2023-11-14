package kz.project.section2.ui.photos;

import androidx.appcompat.app.AppCompatActivity;

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
//        webView.loadUrl("https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXNlcnxlbnwwfHwwfHx8MA%3D%3D");
//        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='image.png' style='width:100%' />", "text/html", "utf-8", null);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);

//        webView.loadUrl("https://vk.com/video-23053131_456241034");
    }

}