package com.jiang.media.ui.detail;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiang.common.widget.ToolBarBuilder;
import com.jiang.media.R;
import com.jiang.media.base.BaseActivity;

/**
 * Created by knowing on 2017/12/6.
 */

public class BrowserActivity extends BaseActivity {

    private String url;
    private String mTitle;

    WebView mWebView;
    private final Handler mHandler = new Handler();
    private WebViewClientBase webViewClient = new WebViewClientBase();

    @Override
    public int getLayoutId() {
        return R.layout.act_browser;
    }

    @Override
    protected void init() {

        url = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");

        new ToolBarBuilder(this)
                .setTitle(TextUtils.isEmpty(mTitle) ? "详情" : mTitle)
                .setNavigationIcon(R.drawable.ic_back)
                .setNavigationOnClickListener(v -> onBackPressed());

        mWebView = (WebView) findViewById(R.id.webView);

        setupWebView();
    }

    @Override
    protected void initInjector() {

    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack() && mWebView.copyBackForwardList().getSize() > 0
                && !mWebView.getUrl().equals(mWebView.copyBackForwardList()
                .getItemAtIndex(0).getOriginalUrl())) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.getSettings().setBlockNetworkImage(true);
        mWebView.setWebViewClient(webViewClient);
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b2 = new AlertDialog
                        .Builder(BrowserActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage(message)
                        .setPositiveButton("确定", (dialog, which) -> result.confirm());
                b2.setCancelable(false);
                b2.create();
                b2.show();
                return true;
            }
        });
        mWebView.loadUrl(url);
    }


    public class WebViewClientBase extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mWebView.getSettings().setBlockNetworkImage(false);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            String errorHtml = "<html><body><h2>找不到网页</h2></body></html>";
            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
        }

    }


    @Override
    protected void onPause() {
        mWebView.reload();
        super.onPause();
    }


    @Override
    public void onDestroy() {
        mWebView.destroy();
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
