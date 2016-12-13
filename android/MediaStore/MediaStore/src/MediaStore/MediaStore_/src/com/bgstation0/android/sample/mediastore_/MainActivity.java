package com.bgstation0.android.sample.mediastore_;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ContentResolver�Ŏʐ^���擾��, GridView�ɒǉ����ĕ\��.
        ContentResolver contentResolver = getContentResolver();	// getContentResolver��contentResolver���擾.
        List<GridItem> griditems = new ArrayList<GridItem>();	// griditems�𐶐�.
        Cursor c = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);	// contentResolver.query�ŊO���X�g���[�W�̉摜�ɃA�N�Z�X.
        if (c.moveToFirst()){	// �ŏ��Ȃ�B
        	for (int i = 0; i < c.getCount(); i++){	// c.getCount�̐�, �J��Ԃ�.
        		
        		// ID�̎擾.
        		String id = c.getString(c.getColumnIndex(MediaStore.Images.Media._ID));	// ID���擾.
        		
        		// �\�����̎擾.
        		String name = c.getString(c.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));	// �\�������擾.
        		
        		// �t�@�C���p�X�̎擾��, ��������Bitmap���擾.(���̂Ƃ��k������.)
        		String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));	// �p�X���擾.
        		BitmapFactory.Options options = new BitmapFactory.Options();	// options�𐶐�.
        		options.inJustDecodeBounds = true;	// �T�C�Y��񂾂��擾.
        		BitmapFactory.decodeFile(path, options);	// BitmapFactory.decodeFile��path����bitmap�̏�񂾂��擾.
        		int scaleWidth = options.outWidth / 80 + 1;	// 80�Ɏ��܂�悤�ɏk�ڌv�Z.
        		int scaleHeight = options.outHeight / 80 + 1;	// 80�Ɏ��܂�悤�ɏk�ڌv�Z.
        		int scale =  Math.max(scaleWidth, scaleHeight);	// �ő�l���Ƃ�.
        		options.inJustDecodeBounds = false;	// ���x�͓ǂݍ���.
        		options.inSampleSize = scale;	// �k�ڎw��.
        		Bitmap bitmap = BitmapFactory.decodeFile(path, options);	// BitmapFactory.decodeFile��path����bitmap�̉摜�擾.
        		
        		// item�̒ǉ�.
        		GridItem item = new GridItem();	// item�𐶐�.
        		item.name = name;	// item.name��name����.
        		item.bitmap = bitmap;	// item.bitmap����.
        		griditems.add(item);	// griditems.add��item��ǉ�.
        		
        		// ����.
        		c.moveToNext();	// moveToNext�Ŏ���.
        		
        	}
        }
        c.close();	// ����.
        GridView gridview1 = (GridView)findViewById(R.id.gridview1);	// findViewById��gridview1���擾.
        GridItemAdapter adapter = new GridItemAdapter(this, R.layout.grid_item, griditems);	// GridItemAdapter�R���X�g���N�^��GridView�̃A�C�e���e���v���[�g��grid_item�ƃO���b�h�f�[�^��griditems���Z�b�g.
        gridview1.setAdapter(adapter);	// gridview1.setAdapter��adapter���Z�b�g����, GridView��Adapter��R�t����.      
    }
    
}