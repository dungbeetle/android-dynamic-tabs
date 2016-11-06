package gobyfragment.we.com.gobyfragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hekeji on 16/11/4.
 */
public class WebViewFragment extends Fragment {

    private View rootView;

    private TextView titleView;

    private RelativeLayout titleLayout;

    private String indexUrl;

    private View loadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.tab_fragment, null);
            indexUrl = getArguments().getString("url");
            this.titleView = (TextView) rootView.findViewById(R.id.title);
            this.titleLayout = (RelativeLayout) rootView.findViewById(R.id.titleLayout);
            this.loadingView = rootView.findViewById(R.id.loading);
            titleLayout.setBackgroundColor(Color.argb(255,
                    Integer.valueOf(ApplicationConfig.getInstance().getNavBarColor().getR()),
                    Integer.valueOf(ApplicationConfig.getInstance().getNavBarColor().getG()),
                    Integer.valueOf(ApplicationConfig.getInstance().getNavBarColor().getB())));
            WebView webView = (WebView) rootView.findViewById(R.id.webview_layout);
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    titleView.setText(title == null ? "" : title);
                }
            });
            webView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    if(){
//
//                    }
//                    return super.shouldOverrideUrlLoading(view, url);
//                }

                //                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    System.out.println("############URL is  + " + url);
                    if (indexUrl != null && !indexUrl.equalsIgnoreCase(url)) {
                        Intent intent = new Intent(getActivity(), SinglePageActivity.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                    return true;
                }

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

                //                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//
//                    return true;
//                }
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

            webView.loadUrl(indexUrl);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }


        return rootView;
    }
}
