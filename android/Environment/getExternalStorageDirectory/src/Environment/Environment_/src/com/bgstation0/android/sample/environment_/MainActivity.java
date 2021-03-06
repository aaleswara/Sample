package com.bgstation0.android.sample.environment_;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	// メンバフィールドの定義.
	Context context = null;	// Context型contextをnullで初期化.
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;	// contextにthisを格納.
    }
    
    // オプションメニューが作成される時.
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
    	
    		// Write file
    		case R.id.menu_write_file:
    			
    			// ファイル名を取得.
    			EditText edittextName = (EditText)findViewById(R.id.edittext_filename);	// findViewByIdでedittext_filenameを取得.
    			Editable textName = edittextName.getText();	// edittextName.getTextでテキスト取得.
    			String filename = textName.toString();	// textName.toStringで文字列取得.
    			
    			// ファイル内容を取得.
    			EditText edittextContent = (EditText)findViewById(R.id.edittext_filecontent);	// findViewByIdでedittext_filecontentを取得.
    			Editable textContent = edittextContent.getText();	// edittextContent.getTextでテキスト取得.
    			String filecontent = textContent.toString();	// textContent.toStringで文字列取得.
    			
    			// ファイル名チェック.
    			if (filename.length() == 0){
    				Toast.makeText(this, "filename is empty!", Toast.LENGTH_SHORT).show();	// "filename is empty!"と表示.
    				break;	// 途中で抜ける.
    			}
    			
    			// 内部ストレージのトップにファイルを書き込む.
    			FileOutputStream out = null; // FileOutputStream型outをnullに初期化.
    			try{	// tryで囲む.
    				String state = Environment.getExternalStorageState();	// Environment.getExternalStorageStateで内部状態stateを取得.
    				if (Environment.MEDIA_MOUNTED.equals(state)){	// MEDIA_MOUNTEDなら.
    					//File ext_dir = context.getExternalFilesDir(null);	// context.getExternalFilesDir(null)で内部ストレージのアプリケーションフォルダトップext_dirを取得.
    					File ext_storage_dir = Environment.getExternalStorageDirectory();	// Environment.getExternalStorageDirectoryでストレージのトップディレクトリext_storage_dirを取得.
    					File ext_file = new File(ext_storage_dir, filename);	// filenameのFileオブジェクトext_fileを生成.
    					out = new FileOutputStream(ext_file);	// ext_fileでFileOutputStreamオブジェクトoutを生成.
    					out.write(filecontent.getBytes());	// out.writeでfilecontentを書き込む.
        				out.close();	// out.closeで閉じる.
        				Toast.makeText(this, ext_file.getPath(), Toast.LENGTH_LONG).show();	// Toastでext_fileのパスを表示.
    				}
    			}
    			catch (FileNotFoundException fileNotEx){	// ファイルが見つからない.
    				Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show();	// "File not found!"と表示.
    			}
    			catch (IOException ioEx){	// IOエラー.
    				Toast.makeText(this, "IO error!", Toast.LENGTH_SHORT).show();	// "IO Error!"と表示.
    			}
    			break;	// ここで抜ける.
    			
    	}
    	return super.onOptionsItemSelected(item);	// 親クラス既定処理
    }
}