package com.github.andrdev.easyenglish.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.github.andrdev.easyenglish.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by taiyokaze on 7/19/15.
 */
public class TubePlayerActivity extends YouTubeBaseActivity  implements
        YouTubePlayer.OnInitializedListener {
    public static final String KEY = "AIzaSyBnzX52XB0WTplnvY7cDprTc9O2kvv4w-w";
    YouTubePlayerView tubePlayerView;
    WebView mWebView;
    String title;
    String videoUrl;

    private MyWebChromeClient mWebChromeClient = null;
    private View mCustomView;
    private RelativeLayout mContentView;
    private FrameLayout mCustomViewContainer;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;

    @Override
    public void onCreate(Bundle savedInstanceStatee) {
        super.onCreate(savedInstanceStatee);
        Log.d("dree", "prelayot");
        setContentView(R.layout.activity_y_player);

        tubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        mWebView = (WebView) findViewById(R.id.webView);

        tubePlayerView.initialize(KEY, this);
        Log.d("dree", "after");
        title = getIntent().getStringExtra("title");
        videoUrl = getIntent().getStringExtra("link");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(videoUrl.substring(videoUrl.lastIndexOf("=")+1));
        Log.d("dree", "suc");

        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("dree", youTubeInitializationResult.toString());
        tubePlayerView.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadUrl("http://m.youtube.com/watch?v=" + videoUrl.substring(videoUrl.lastIndexOf("=") + 1) + "?autoplay=1");
        Log.d("dreeyou", "https://youtube.com/v/" + videoUrl.substring(videoUrl.lastIndexOf("=") + 1) + "?autoplay=1)");
//                mWebView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mWebView.getVisibility() == View.VISIBLE){
            mWebView.loadUrl("http://m.youtube.com/watch?v="+videoUrl.substring(videoUrl.lastIndexOf("=")+1) + "?autoplay=1");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mWebView.loadUrl("about:blank");
    }

    @Override
    public void onBackPressed() {
        Log.d("dree", "back");
        if (mCustomViewContainer != null)
            mWebChromeClient.onHideCustomView();
        else if (mWebView.canGoBack())
            mWebView.goBack();
        else
        mWebView.loadUrl("about:blank");
        finish();
    }
    public class MyWebChromeClient extends WebChromeClient {
        FrameLayout.LayoutParams LayoutParameters = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);


        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
//            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
//                callback.onCustomViewHidden();
                return;
            }
//            mContentView = (RelativeLayout) findViewById(R.id.activity);
//            mContentView.setVisibility(View.GONE);
//            mCustomViewContainer = new FrameLayout(SomeTube.this);
//            mCustomViewContainer.setLayoutParams(LayoutParameters);
//            mCustomViewContainer.setBackgroundResource(android.R.color.black);
//            view.setLayoutParams(LayoutParameters);
//            mCustomViewContainer.addView(view);
//            mCustomView = view;
//            mCustomViewCallback = callback;
//            mCustomViewContainer.setVisibility(View.VISIBLE);
//            setContentView(mCustomViewContainer);
        }

        @Override
        public void onHideCustomView() {
            if (mCustomView == null) {
                return;
            } else {
                // Hide the custom view.
                mCustomView.setVisibility(View.GONE);
                // Remove the custom view from its container.
                mCustomViewContainer.removeView(mCustomView);
                mCustomView = null;
                mCustomViewContainer.setVisibility(View.GONE);
                mCustomViewCallback.onCustomViewHidden();
                // Show the content view.
                mContentView.setVisibility(View.VISIBLE);
                setContentView(mContentView);
            }
        }
    }
}
