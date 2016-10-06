package com.bgstation0.android.sample.asynctask_;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{	// View.OnClickListener������.

	// �����o�t�B�[���h�̒�`
	CustomAsyncTask task = null;	// CustomAsyncTask�I�u�W�F�N�gtask��null��.
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // button1���擾��, OnClickListener�Ƃ��Ď��g���Z�b�g.
        Button button1 = (Button)findViewById(R.id.button1);	// findViewById��R.id.button1���擾.
        button1.setOnClickListener(this);	// button1.setOnClickListener��this(���g)���Z�b�g.
    }
    
    // View.OnClickListener�C���^�t�F�[�X�̃I�[�o�[���C�h���\�b�h������.
    public void onClick(View v){	// onClick���I�[�o�[���C�h.
    	// CustomAsyncTask�ɂ��񓯊������𐶐���, ���s.
    	task = new CustomAsyncTask(this);	// CustomAsyncTask�I�u�W�F�N�g���쐬��, task�Ɋi�[.
    	task.execute(10);	// task.execute��10��n���Ď��s.
    }
}