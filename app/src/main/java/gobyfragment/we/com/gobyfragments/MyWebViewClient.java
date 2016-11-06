package gobyfragment.we.com.gobyfragments;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hekeji on 16/10/27.
 */
public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }


}
