package wangfeixixi.sampleweb;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;


public class MWebChromeClient extends WebChromeClient {
    //提示框
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        //false 允许弹出提示框
        //true 不允许弹出提示框，应用自己来处理，注意：一定要result.confirm或者result.cancel才能捕捉到下一次
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        result.cancel();
        return true;
//        return super.onJsAlert(view, url, message, result);
    }

    //对话框 通过alert方法拦截进行数据交换
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm("需要给js传递得数据");
        return true;
//        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
//        Toast.makeText(view.getContext(), title, Toast.LENGTH_SHORT).show();
    }


    //进度条
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }
}
