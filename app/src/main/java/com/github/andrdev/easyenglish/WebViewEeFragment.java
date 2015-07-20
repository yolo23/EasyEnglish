package com.github.andrdev.easyenglish;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by taiyokaze on 7/16/15.
 */
public class WebViewEeFragment extends Fragment {
        private WebView mWebView;
        private boolean mIsAvailable = false;

        public WebViewEeFragment() {
     }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mWebView != null) {
            mWebView.destroy();
        }
        mWebView = new WebView(getActivity());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mIsAvailable = true;
        return mWebView;
    }

        @Override
        public void onPause() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.onPause();
        }
        super.onPause();
    }

        @Override
        public void onResume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.onResume();
        }
        super.onResume();
    }

        @Override
        public void onDestroyView() {
        mIsAvailable = false;
        super.onDestroyView();
    }

        @Override
        public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        mIsAvailable = false;
        super.onDestroy();
    }

    public WebView getWebView() {
        return mIsAvailable ? mWebView : null;
    }
}
