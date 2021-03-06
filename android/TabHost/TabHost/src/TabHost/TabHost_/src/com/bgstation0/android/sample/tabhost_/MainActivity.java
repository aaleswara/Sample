package com.bgstation0.android.sample.tabhost_;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //TabHostオブジェクトの取得と初期化.
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);	// tabHostを取得.
        tabHost.setup();	// tabHost.setupで初期化.
        
        // タブの追加.
        // タブ1
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");	// tabHost.newTabSpecで"tab1"というタグを付けたタブtab1を生成.
        tab1.setIndicator("タブ1");	// tab1.setIndicatorでタブ部分にテキスト"タブ1"をセット.
        tab1.setContent(R.id.contentlayout1);	// tab1.setContentでコンテンツ部分にcontentlayout1をセット.
        tabHost.addTab(tab1);	// tabHost.addTabでtab1を追加.
        // タブ2
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");	// tabHost.newTabSpecで"tab2"というタグを付けたタブtab2を生成.
        tab2.setIndicator("タブ2");	// tab2.setIndicatorでタブ部分にテキスト"タブ2"をセット.
        tab2.setContent(R.id.contentlayout2);	// tab2.setContentでコンテンツ部分にcontentlayout2をセット.
        tabHost.addTab(tab2);	// tabHost.addTabでtab2を追加.
        // タブ3
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3");	// tabHost.newTabSpecで"tab3"というタグを付けたタブtab3を生成.
        tab3.setIndicator("タブ3");	// tab3.setIndicatorでタブ部分にテキスト"タブ3"をセット.
        tab3.setContent(R.id.contentlayout3);	// tab3.setContentでコンテンツ部分にcontentlayout3をセット.
        tabHost.addTab(tab3);	// tabHost.addTabでtab3を追加.
    }
}
