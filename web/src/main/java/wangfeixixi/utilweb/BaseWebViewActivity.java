package wangfeixixi.utilweb;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONObject;

import wangfei.swipeback.SwipeBackActivity;

public abstract class BaseWebViewActivity extends SwipeBackActivity {

    public WebView webView;
    private String url = "https://github.com/wangfeixixi";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @SuppressLint("JavascriptInterface")
    protected void initData() {
        webView = getWebView();
        setWebvieweSettings(webView);
        webView.addJavascriptInterface(getJSObject(), getJSObjectName());//和html调用得对象名字一样
        webView.setWebViewClient(getWebViewClient());//监控webview加载网页得状态
        webView.setWebChromeClient(getWebChromeClient());//js交互
        Intent intent = getIntent();
        if (intent != null) {
            String urlCurrent = intent.getStringExtra("url");
            if (urlCurrent != null)
                url = urlCurrent;
        }
        loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    protected void loadUrl(String url) {
        try {
            CookieSyncManager.createInstance(webView.getContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeAllCookie();
            JSONObject jsonObject = new JSONObject();
            cookieManager.setCookie(url, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
        webView.loadUrl(url);
    }

    protected void setWebvieweSettings(WebView wv) {
        //获取当前webview得setting
        WebSettings settings = wv.getSettings();
        settings.setBuiltInZoomControls(false);//是否能够开启webview得自带得缩放功能
        settings.setDefaultFontSize(16);//设置默认字体大小
        settings.setJavaScriptEnabled(true);//js和native互调
        //webview默认情况下可以访问手机assets文件夹和手机文件系统
        settings.setAllowFileAccess(false);//是否显示访问手机文件系统
        settings.setDatabaseEnabled(true);//设置可以创建数据库
        settings.setDomStorageEnabled(true);//允许localstorage和sesstionstroage
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存
    }

    /**
     * setcontentview或者bind view
     */
    protected abstract void initView();

    /**
     * 获取WebView控件
     */
    protected abstract WebView getWebView();

    /**
     * 获取js对象得名字和后台约定俗称得
     */
    protected abstract String getJSObjectName();

    /**
     * 获取js对象
     */
    protected abstract Object getJSObject();

    /**
     * 监控网页加载情况
     */
    protected abstract WebViewClient getWebViewClient();

    /**
     * 和js互动
     */
    protected abstract WebChromeClient getWebChromeClient();
}
