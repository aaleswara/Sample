package com.bgstation0.android.sample.webview.hittestresult_;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener{	// View.OnClickListener, View.OnLongClickListenerを実装.

	// メンバフィールドの定義
	public WebView webView;	// WebViewオブジェクトwebView
	public CustomWebViewClient customWVC;	// CustomWebViewClientオブジェクトcustomWVC
	public CustomWebChromeClient customWCC;	// CustomWebChromeClientオブジェクトcustomWCC
	public WebSettings webSettings;			// WebSettingsオブジェクトwebSettings
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // loadbuttonを取得し, OnClickListenerとして自身をセット.
        Button loadButton = (Button)findViewById(R.id.loadbutton);	// loadButtonを取得.
        loadButton.setOnClickListener(this);	// loadButton.setOnClickListenerでthisをセット.
        
        // WebViewの初期設定.
        webView = (WebView)findViewById(R.id.webview);	// webViewを取得.
        webView.setOnLongClickListener(this);	// webView.setOnLongClickListenerでthisをセット.
        customWVC = new CustomWebViewClient(this);	// CustomWebViewClientオブジェクトcustomWVCを作成.
        webView.setWebViewClient(customWVC);	// webView.setWebViewClientでcustomWVCをセット.
        customWCC = new CustomWebChromeClient(this);	// CustomWebChromeClientオブジェクトcustomWCCを作成.
        webView.setWebChromeClient(customWCC);	// webView.setWebChromeClientでcustomWCCをセット.
        webSettings = webView.getSettings();	// webView.getSettingsでwebSettingsを取得.
        webSettings.setJavaScriptEnabled(true);	// webSettings.setJavaScriptEnabledでJavaScriptを有効にする.
        
    }
    
    // メニューが作成された時.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	
    	// メニューの作成
    	getMenuInflater().inflate(R.menu.main, menu);	// getMenuInflaterでMenuItemを取得し, そのままinflateで指定されたR.menu.mainを元にmenuを作成.
    	return true;	// trueを返す.
    	
    }
    
    // メニューが選択された時.
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    
    	// どのメニューが選択されたか.
    	switch (item.getItemId()){	// アイテムIDごとに振り分け.
    	
    		// GoBack
    		case R.id.menu_goback:
    			
    			// GoBackブロック
    			{
    				
    				// 戻れるなら戻る, 戻れなかったら"戻れません!"と表示.
    				if (webView.canGoBack()){	// canGoBackで前に戻れるかチェック.
    					
    					// 1つ前のページに戻る.
    					webView.goBack();	// webView.goBackで1つ前のページに戻る.

    				}
    				else{
    					
    					// "戻れません!"と表示.
    					Toast.makeText(this, "戻れません!", Toast.LENGTH_LONG).show();	// Toastで"戻れません!"と表示.
    					
    				}
    				
    			}
    			
    			break;
    			
    	}
    	
    	return super.onOptionsItemSelected(item);	// 親クラス既定処理
    	
    }
    
    // View.OnClickListenerインタフェースのオーバーライドメソッドを実装.
    public void onClick(View v){	// onClickをオーバーライド.
    	
    	// ボタンのidをごとに処理を振り分ける.
    	switch (v.getId()) {	// v.getIdでView(Button)のidを取得.
    		
    		case R.id.loadbutton:	// R.id.loadbuttonの時.
    			
    			// navigateButtonブロック
				{
					
					// urlBarのurlを取得.
    				EditText urlBar = (EditText)findViewById(R.id.urlbar);	// urlBarを取得.
    				String url = urlBar.getText().toString();	// urlBar.getText().toString()でurlを取得.
  
    				// webViewでurlのサイトを表示.
    				webView.loadUrl(url);	// webView.loadUrlでurlのサイトを表示.
    				
    				// 抜ける.
    				break;	// breakで抜ける.
    				
				}
				
    	}
    	
    }
    
    // View.OnLongClickListenerインタフェースのオーバーライドメソッドを実装.
    public boolean onLongClick(View v){	// onLongClickをオーバーライド.
    
    	// 長押しされた部分の情報を取得.
    	WebView wv = (WebView)v;	// vをWebViewのwvにキャスト.
    	WebView.HitTestResult hr = wv.getHitTestResult();	// wv.getHitTestResultでWebView.HitTestResult型hrを取得.
    	
    	// 長押しされた部分のタイプを表示.
    	switch (hr.getType()){
    	
    		// UNKNOWN_TYPEの場合.
    		case WebView.HitTestResult.UNKNOWN_TYPE:
    			
    			// UNKNOWN_TYPEブロック.
    			{
    				
    				// "UNKNOWN_TYPE"と表示.
    				Toast.makeText(this, "UNKNOWN_TYPE", Toast.LENGTH_LONG).show();	// Toastで"UNKNOWN_TYPE"と表示.
    			
    			}
    			
    			break;
    			
    		// ANCHOR_TYPEの場合.	
    		case WebView.HitTestResult.ANCHOR_TYPE:
    			
    			// ANCHOR_TYPEブロック.
    			{
    				
    				// "ANCHOR_TYPE"と表示.
    				Toast.makeText(this, "ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toastで"ANCHOR_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// PHONE_TYPEの場合.	
    		case WebView.HitTestResult.PHONE_TYPE:
    			
    			// PHONE_TYPEブロック.
    			{
    				
    				// "PHONE_TYPE"と表示.
    				Toast.makeText(this, "PHONE_TYPE", Toast.LENGTH_LONG).show();	// Toastで"PHONE_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// GEO_TYPEの場合.	
    		case WebView.HitTestResult.GEO_TYPE:
    			
    			// GEO_TYPEブロック.
    			{
    				
    				// "GEO_TYPE"と表示.
    				Toast.makeText(this, "GEO_TYPE", Toast.LENGTH_LONG).show();	// Toastで"GEO_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// EMAIL_TYPEの場合.	
    		case WebView.HitTestResult.EMAIL_TYPE:
    			
    			// EMAIL_TYPEブロック.
    			{
    				
    				// "EMAIL_TYPE"と表示.
    				Toast.makeText(this, "EMAIL_TYPE", Toast.LENGTH_LONG).show();	// Toastで"EMAIL_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// IMAGE_TYPEの場合.	
    		case WebView.HitTestResult.IMAGE_TYPE:
    			
    			// IMAGE_TYPEブロック.
    			{
    				
    				// "IMAGE_TYPE"と表示.
    				Toast.makeText(this, "IMAGE_TYPE", Toast.LENGTH_LONG).show();	// Toastで"IMAGE_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// IMAGE_ANCHOR_TYPEの場合.	
    		case WebView.HitTestResult.IMAGE_ANCHOR_TYPE:
    			
    			// IMAGE_ANCHOR_TYPEブロック.
    			{
    				
    				// "IMAGE_ANCHOR_TYPE"と表示.
    				Toast.makeText(this, "IMAGE_ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toastで"IMAGE_ANCHOR_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// SRC_ANCHOR_TYPEの場合.	
    		case WebView.HitTestResult.SRC_ANCHOR_TYPE:
    			
    			// SRC_ANCHOR_TYPEブロック.
    			{
    				
    				// "SRC_ANCHOR_TYPE"と表示.
    				Toast.makeText(this, "SRC_ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toastで"SRC_ANCHOR_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// SRC_IMAGE_ANCHOR_TYPEの場合.	
    		case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
    			
    			// SRC_IMAGE_ANCHOR_TYPEブロック.
    			{
    				
    				// "SRC_IMAGE_ANCHOR_TYPE"と表示.
    				Toast.makeText(this, "SRC_IMAGE_ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toastで"SRC_IMAGE_ANCHOR_TYPE"と表示.
    				
    			}
    			
    			break;
    		
    		// EDIT_TEXT_TYPEの場合.	
    		case WebView.HitTestResult.EDIT_TEXT_TYPE:
    			
    			// EDIT_TEXT_TYPEブロック.
    			{
    				
    				// "EDIT_TEXT_TYPE"と表示.
    				Toast.makeText(this, "EDIT_TEXT_TYPE", Toast.LENGTH_LONG).show();	// Toastで"EDIT_TEXT_TYPE"と表示.
    				
    			}
    			
    			break;
    			
    		// それ以外.
    		default:
    			
    			// "Unexpected Type!"と表示.
    			Toast.makeText(this, "Unexpected Type!", Toast.LENGTH_LONG).show();	// Toastで"Unexpected Type!"と表示.
    			
    			break;
    			
    	}
    	
    	// hr.getExtraで取得できるimg-srcを表示.
    	String extra = hr.getExtra();	// hr.getExtraでextraを取得.
    	EditText et = (EditText)findViewById(R.id.edittext);	// etを取得.
    	et.setText(extra);	// et.setTextでextraを表示.
    	
    	return true;	// trueを返すと, OnClickは発生しない.
    	
    }
    
}