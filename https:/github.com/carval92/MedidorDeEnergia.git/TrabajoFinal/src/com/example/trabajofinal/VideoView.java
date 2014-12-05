package com.example.trabajofinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class VideoView extends Activity {
	
	String videoURL = "http://www.youtube.com/embed/MFqsYkz2C-4";
	WebView mWebView = null;
	ProgressBar barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_view);
		
		
		initwebView();	
		barra = (ProgressBar) findViewById(R.id.progressBar1);
	}
	
	private void initwebView() {
		mWebView = (WebView) findViewById(R.id.webView);
		mWebView.getSettings().setPluginState(PluginState.ON);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebChromeClient(new MyChromeClient(){
			public void onProgressChanged(WebView view, int progress) {
				barra.setProgress(progress);
			}});
		mWebView.loadUrl(videoURL);
	}
 
	class MyChromeClient extends WebChromeClient {
 
		@Override
		public void onShowCustomView(View view, CustomViewCallback callback) {
			/*Intent intents = new Intent(Video.this, Fullscreen.class);
			intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intents.putExtra("video", videoURL);
			startActivity(intents);*/
 
		}
 
	}
}