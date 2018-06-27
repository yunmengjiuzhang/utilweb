package wangfeixixi.sampleweb;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wangfei.utilweb.BaseWebViewActivity;

public class MainActivity extends BaseWebViewActivity {

    @Override
    protected int initViewIds() {
        return R.layout.activity_main;
    }

    @Override
    public WebView getWebView() {
        return (WebView) findViewById(R.id.webview);
    }

    @Override
    protected String getJSObjectName() {
        return null;
    }

    @Override
    protected Object getJSObject() {
        return null;
    }

    @Override
    protected WebViewClient getWebViewClient() {
        return new WebViewClient();
    }

    @Override
    public WebChromeClient getWebChromeClient() {
        return null;
    }
}
