package wangfeixixi.sampleweb;


import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MWebviewClient extends WebViewClient {
    //监控网页资源加载情况
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    //一般用于页面开始加载得时候弹出对话框，
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//        Toast.makeText(view.getContext(), "加载中...", Toast.LENGTH_SHORT).show();
    }

    //页面加载完成之后隐藏对话框
    @Override
    public void onPageFinished(WebView view, String url) {
//        super.onPageFinished(view, url);
//        Toast.makeText(view.getContext(), "加载完成", Toast.LENGTH_SHORT).show();

    }

    //旧版本网页加载错误处理
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//        super.onReceivedError(view, errorCode, description, failingUrl);
        //统一处理加载错误
//        view.loadUrl("afsdfasfa");//加载错误就加载本地html

    }

    //6.0网页加载错误处理
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//        super.onReceivedError(view, request, error);
        //调回上面得方法
        this.onReceivedError(view, 0, "", "");
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        return super.shouldOverrideUrlLoading(view, url);
        //return true拦截url不处理
        //return false webview加载url
        Toast.makeText(view.getContext(), url, Toast.LENGTH_SHORT).show();
        if (url.startsWith("http")) {
            view.loadUrl(url);
        }
        return true;
    }

//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        return super.shouldOverrideUrlLoading(view, zzSring.valueOf(request));
//    }

//    @Override
//    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
//        return super.shouldOverrideKeyEvent(webView, keyEvent);
//    }
}
