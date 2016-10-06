package com.bgstation0.android.sample.intentservice_;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CustomIntentService extends IntentService {

	// �����o�t�B�[���h�̒�`
	private final String TAG = "CustomIntentService";	// TAG��"CustomIntentService"�Ƃ���.
	
	// �R���X�g���N�^(���̃R���X�g���N�^���K�v�炵��.)
	public CustomIntentService(){
		super("CustomIntentService");	// �e�R���X�g���N�^��"CustomIntentService"��n��.
	}
	
	// �����t���R���X�g���N�^(������͌Ă΂�Ȃ��͗l.)
	public CustomIntentService(String name){
		super(name);
	}
	
	// �񓯊�����
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
    	// try�ň͂�.
    	try{
    		Thread.sleep(10000);	// 10�b�x�~
    		int param = intent.getIntExtra("param", 0);	// �L�[��"param"�Ƃ����l�����o����param�Ɋi�[.(���������ꍇ�̃f�t�H���g�l��0.)
    		Log.d(TAG, "CustomIntentService finish! param = " + param);	// Log.d��"CustomIntentService finish!"��param�̒l��\��.
    	}
    	catch (Exception ex){
    		Log.d(TAG, "ex = " + ex.toString());	// Log.d��ex��\��.
    	}
	}

}