package wangfeixixi.sampleweb;


import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


public class MWebJsBrige {

    private WebView webView;

    public MWebJsBrige(WebView webView) {
        this.webView = webView;
    }

    //提供被调用得方法，再该方法上加注解
    @JavascriptInterface
    public void Show(String s) {

        Toast.makeText(webView.getContext(), s, Toast.LENGTH_SHORT).show();
    }

}
