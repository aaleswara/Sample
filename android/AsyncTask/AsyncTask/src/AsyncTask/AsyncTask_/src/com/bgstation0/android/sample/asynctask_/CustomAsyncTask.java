package com.bgstation0.android.sample.asynctask_;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class CustomAsyncTask extends AsyncTask<Integer, Integer, String> {	// 非同期処理に渡すパラメータがInteger, 進捗状態がInteger, 非同期処理の結果がStringのCustomAsyncTask.

	// メンバフィールドの定義
	Context context;	// Context型context
	
	// コンストラクタ
	public CustomAsyncTask(Context context){
		this.context = context;	// this.contextメンバに格納.
	}
	
	// 非同期処理
	@Override
	protected String doInBackground(Integer... params){
		// オブジェクトの宣言
		String ret = "";	// 戻り値の文字列retに""をセット.
		// tryで囲む.
		try{
			Thread.sleep(10000);	// 10秒休止
			if (params.length > 0){	// paramsの要素数が1つでもある.
				ret = "params[0] = " + params[0];	// retに0番目の引数.
			}
			else{
				ret = "params[0] = nothing";	// retにnothing.
			}
		}
		catch (Exception ex){
			ret = "excaption";	// retにexception.
		}
		return ret;
	}
	
	// UIスレッドの更新
	@Override
	protected void onPostExecute(String result){
		// textview1の更新
		Activity activity = (Activity)context;	// contextをactivityにキャスト.
		TextView textview1 = (TextView)activity.findViewById(R.id.textview1);	// textview1を取得.
		textview1.setText(result);	// textview1.setTextにresultをセット.
	}
	
}
