package gobyfragment.we.com.gobyfragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hekeji on 16/11/5.
 */
public class SinglePageActivity extends FragmentActivity {

    private TextView titleView;

    private RelativeLayout titleLayout, backLayout;
    private View loadingView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_page);

        String url = getIntent().getStringExtra("url");
        this.titleView = (TextView) this.findViewById(R.id.title);
        this.titleLayout = (RelativeLayout) this.findViewById(R.id.titleLayout);
        this.backLayout = (RelativeLayout) this.findViewById(R.id.back_layout);
        this.backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.loadingView = this.findViewById(R.id.loading);
        titleLayout.setBackgroundColor(Color.argb(255,
                Integer.valueOf(ApplicationConfig.getInstance().getNavBarColor().getR()),
                Integer.valueOf(ApplicationConfig.getInstance().getNavBarColor().getG()),
                Integer.valueOf(ApplicationConfig.getInstance().getNavBarColor().getB())));
        WebView webView = (WebView) this.findViewById(R.id.webview_layout);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleView.setText(title == null ? "" : title);
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingView.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.setVisibility(View.GONE);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);

        webView.loadUrl(url);
    }
}
