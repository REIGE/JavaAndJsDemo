package com.reige.javaandjsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCallJs;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnCallJs = (Button)findViewById(R.id.btn_callJs);
        webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        //支持js
        webSettings.setJavaScriptEnabled(true);
        //支持缩放
        webSettings.setBuiltInZoomControls(true);
        //设置 链接不跳转到浏览器
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });
        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJsInterface(),"Android");
        //加载html
        webView.loadUrl("file:///android_asset/JavaAndJs.html");
        btnCallJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javaCallJs("+"'"+"java调用js"+"'"+")");
            }
        });
    }

    class AndroidAndJsInterface{
        @JavascriptInterface
        public void showToast(){
            Toast.makeText(MainActivity.this, "js调用java", Toast.LENGTH_SHORT).show();
        }
    }
}
