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

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener{	// View.OnClickListener, View.OnLongClickListener������.

	// �����o�t�B�[���h�̒�`
	public WebView webView;	// WebView�I�u�W�F�N�gwebView
	public CustomWebViewClient customWVC;	// CustomWebViewClient�I�u�W�F�N�gcustomWVC
	public CustomWebChromeClient customWCC;	// CustomWebChromeClient�I�u�W�F�N�gcustomWCC
	public WebSettings webSettings;			// WebSettings�I�u�W�F�N�gwebSettings
	
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
    	
    	// ���������ꂽ�����̃^�C�v��\��.
    	switch (hr.getType()){
    	
    		// UNKNOWN_TYPE�̏ꍇ.
    		case WebView.HitTestResult.UNKNOWN_TYPE:
    			
    			// UNKNOWN_TYPE�u���b�N.
    			{
    				
    				// "UNKNOWN_TYPE"�ƕ\��.
    				Toast.makeText(this, "UNKNOWN_TYPE", Toast.LENGTH_LONG).show();	// Toast��"UNKNOWN_TYPE"�ƕ\��.
    			
    			}
    			
    			break;
    			
    		// ANCHOR_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.ANCHOR_TYPE:
    			
    			// ANCHOR_TYPE�u���b�N.
    			{
    				
    				// "ANCHOR_TYPE"�ƕ\��.
    				Toast.makeText(this, "ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toast��"ANCHOR_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// PHONE_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.PHONE_TYPE:
    			
    			// PHONE_TYPE�u���b�N.
    			{
    				
    				// "PHONE_TYPE"�ƕ\��.
    				Toast.makeText(this, "PHONE_TYPE", Toast.LENGTH_LONG).show();	// Toast��"PHONE_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// GEO_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.GEO_TYPE:
    			
    			// GEO_TYPE�u���b�N.
    			{
    				
    				// "GEO_TYPE"�ƕ\��.
    				Toast.makeText(this, "GEO_TYPE", Toast.LENGTH_LONG).show();	// Toast��"GEO_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// EMAIL_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.EMAIL_TYPE:
    			
    			// EMAIL_TYPE�u���b�N.
    			{
    				
    				// "EMAIL_TYPE"�ƕ\��.
    				Toast.makeText(this, "EMAIL_TYPE", Toast.LENGTH_LONG).show();	// Toast��"EMAIL_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// IMAGE_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.IMAGE_TYPE:
    			
    			// IMAGE_TYPE�u���b�N.
    			{
    				
    				// "IMAGE_TYPE"�ƕ\��.
    				Toast.makeText(this, "IMAGE_TYPE", Toast.LENGTH_LONG).show();	// Toast��"IMAGE_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// IMAGE_ANCHOR_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.IMAGE_ANCHOR_TYPE:
    			
    			// IMAGE_ANCHOR_TYPE�u���b�N.
    			{
    				
    				// "IMAGE_ANCHOR_TYPE"�ƕ\��.
    				Toast.makeText(this, "IMAGE_ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toast��"IMAGE_ANCHOR_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// SRC_ANCHOR_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.SRC_ANCHOR_TYPE:
    			
    			// SRC_ANCHOR_TYPE�u���b�N.
    			{
    				
    				// "SRC_ANCHOR_TYPE"�ƕ\��.
    				Toast.makeText(this, "SRC_ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toast��"SRC_ANCHOR_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// SRC_IMAGE_ANCHOR_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
    			
    			// SRC_IMAGE_ANCHOR_TYPE�u���b�N.
    			{
    				
    				// "SRC_IMAGE_ANCHOR_TYPE"�ƕ\��.
    				Toast.makeText(this, "SRC_IMAGE_ANCHOR_TYPE", Toast.LENGTH_LONG).show();	// Toast��"SRC_IMAGE_ANCHOR_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    		
    		// EDIT_TEXT_TYPE�̏ꍇ.	
    		case WebView.HitTestResult.EDIT_TEXT_TYPE:
    			
    			// EDIT_TEXT_TYPE�u���b�N.
    			{
    				
    				// "EDIT_TEXT_TYPE"�ƕ\��.
    				Toast.makeText(this, "EDIT_TEXT_TYPE", Toast.LENGTH_LONG).show();	// Toast��"EDIT_TEXT_TYPE"�ƕ\��.
    				
    			}
    			
    			break;
    			
    		// ����ȊO.
    		default:
    			
    			// "Unexpected Type!"�ƕ\��.
    			Toast.makeText(this, "Unexpected Type!", Toast.LENGTH_LONG).show();	// Toast��"Unexpected Type!"�ƕ\��.
    			
    			break;
    			
    	}
    	
    	return true;	// true��Ԃ���, OnClick�͔������Ȃ�.
    	
    }
    
}