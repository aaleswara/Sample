package com.bgstation0.android.sample.downloadmanager.request_;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener{	// View.OnClickListener, View.OnLongClickListener������.

	// �����o�t�B�[���h�̒�`
	public WebView webView;	// WebView�I�u�W�F�N�gwebView
	public CustomWebViewClient customWVC;	// CustomWebViewClient�I�u�W�F�N�gcustomWVC
	public CustomWebChromeClient customWCC;	// CustomWebChromeClient�I�u�W�F�N�gcustomWCC
	public WebSettings webSettings;			// WebSettings�I�u�W�F�N�gwebSettings
	public DownloadManager downloadManager = null;	// DownloadManager�^downloadManager��null�ɃZ�b�g.
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // loadbutton���擾��, OnClickListener�Ƃ��Ď��g���Z�b�g.
        Button loadButton = (Button)findViewById(R.id.loadbutton);	// loadButton���擾.
        loadButton.setOnClickListener(this);	// loadButton.setOnClickListener��this���Z�b�g.
        
        // WebView�̏����ݒ�.
        webView = (WebView)findViewById(R.id.webview);	// webView���擾.
        webView.setOnLongClickListener(this);	// webView.setOnLongClickListener��this���Z�b�g.
        customWVC = new CustomWebViewClient(this);	// CustomWebViewClient�I�u�W�F�N�gcustomWVC���쐬.
        webView.setWebViewClient(customWVC);	// webView.setWebViewClient��customWVC���Z�b�g.
        customWCC = new CustomWebChromeClient(this);	// CustomWebChromeClient�I�u�W�F�N�gcustomWCC���쐬.
        webView.setWebChromeClient(customWCC);	// webView.setWebChromeClient��customWCC���Z�b�g.
        webSettings = webView.getSettings();	// webView.getSettings��webSettings���擾.
        webSettings.setJavaScriptEnabled(true);	// webSettings.setJavaScriptEnabled��JavaScript��L���ɂ���.
        
        // DownloadManager�̊m��.
        if (downloadManager == null){	// downloadManager��null�Ȃ�.
        	downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);	// getSystemService��DOWNLOAD_SERVICE���擾��, downloadManager�Ɋi�[.
        }
        
    }
    
    // ���j���[���쐬���ꂽ��.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	
    	// ���j���[�̍쐬
    	getMenuInflater().inflate(R.menu.main, menu);	// getMenuInflater��MenuItem���擾��, ���̂܂�inflate�Ŏw�肳�ꂽR.menu.main������menu���쐬.
    	return true;	// true��Ԃ�.
    	
    }
    
    // ���j���[���I�����ꂽ��.
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    
    	// �ǂ̃��j���[���I�����ꂽ��.
    	switch (item.getItemId()){	// �A�C�e��ID���ƂɐU�蕪��.
    	
    		// GoBack
    		case R.id.menu_goback:
    			
    			// GoBack�u���b�N
    			{
    				
    				// �߂��Ȃ�߂�, �߂�Ȃ�������"�߂�܂���!"�ƕ\��.
    				if (webView.canGoBack()){	// canGoBack�őO�ɖ߂�邩�`�F�b�N.
    					
    					// 1�O�̃y�[�W�ɖ߂�.
    					webView.goBack();	// webView.goBack��1�O�̃y�[�W�ɖ߂�.

    				}
    				else{
    					
    					// "�߂�܂���!"�ƕ\��.
    					Toast.makeText(this, "�߂�܂���!", Toast.LENGTH_LONG).show();	// Toast��"�߂�܂���!"�ƕ\��.
    					
    				}
    				
    			}
    			
    			break;
    			
    	}
    	
    	return super.onOptionsItemSelected(item);	// �e�N���X���菈��
    	
    }
    
    // View.OnClickListener�C���^�t�F�[�X�̃I�[�o�[���C�h���\�b�h������.
    public void onClick(View v){	// onClick���I�[�o�[���C�h.
    	
    	// �{�^����id�����Ƃɏ�����U�蕪����.
    	switch (v.getId()) {	// v.getId��View(Button)��id���擾.
    		
    		case R.id.loadbutton:	// R.id.loadbutton�̎�.
    			
    			// navigateButton�u���b�N
				{
					
					// urlBar��url���擾.
    				EditText urlBar = (EditText)findViewById(R.id.urlbar);	// urlBar���擾.
    				String url = urlBar.getText().toString();	// urlBar.getText().toString()��url���擾.
  
    				// webView��url�̃T�C�g��\��.
    				webView.loadUrl(url);	// webView.loadUrl��url�̃T�C�g��\��.
    				
    				// ������.
    				break;	// break�Ŕ�����.
    				
				}
				
    	}
    	
    }
    
    // View.OnLongClickListener�C���^�t�F�[�X�̃I�[�o�[���C�h���\�b�h������.
    public boolean onLongClick(View v){	// onLongClick���I�[�o�[���C�h.
    
    	// ���������ꂽ�����̏����擾.
    	WebView wv = (WebView)v;	// v��WebView��wv�ɃL���X�g.
    	WebView.HitTestResult hr = wv.getHitTestResult();	// wv.getHitTestResult��WebView.HitTestResult�^hr���擾.
    	if (hr.getType() == WebView.HitTestResult.IMAGE_TYPE){	// �������������P���ȉ摜�݂̂̏ꍇ.
    		String url = hr.getExtra();	// hr.getExtra�Ŏ擾����URL��url�Ɋi�[.
    		Uri uri = Uri.parse(url);	// Uri.parse��url���p�[�X��, uri�Ɋi�[.
    		String downloadFilename = uri.getLastPathSegment();	// downloadUri.getLastPathSegment�Ńt�@�C�����̕����������o��.
    		// �_�E�����[�h���N�G�X�g�̍쐬
        	DownloadManager.Request request = new DownloadManager.Request(uri);	// DownloadManager.Request�I�u�W�F�N�g��uri��n���č쐬, request�Ɋi�[.
        	request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "/" + downloadFilename);	// request.setDestinationInExternalFilesDir�Ń_�E�����[�h����w��.(�ۑ���t�H���_��Environment.DIRECTORY_DOWNLOADS�ɕύX.)
        	request.setTitle("DownloadManager_");	// �ʒm��ʂ̃^�C�g�����Z�b�g.
        	request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);	// �l�b�g���[�N�^�C�v�̓��o�C��(3G/LTE)��WiFi����.
        	request.setMimeType("application/octet-stream");	// MIME�^�C�v��"application/octet-stream".
        	// �_�E�����[�h���V�[�o�[�̍쐬.
        	DownloadReceiver downloadReceiver = new DownloadReceiver(this);	// DownloadReceiver�^downloadReceiver���쐬.
        	registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));  // downloadReceiver��ACTION_DOWNLOAD_COMPLETE��IntentFilter�̃C���X�^���X��registerReceiver�œo�^.
        	// ���N�G�X�g�𓊂���.
        	downloadManager.enqueue(request);	// downloadManager��request��ǉ�.
    	}
    	return true;	// true��Ԃ���, OnClick�͔������Ȃ�.
    	
    }
    
}