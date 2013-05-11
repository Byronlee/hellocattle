package com.niu.hellocattle;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BorthActivity extends Activity implements OnClickListener, OnCompletionListener{
	/**
     * 声音播放用的数据保持。
     */
    private MediaPlayer mMp;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //强制全屏
        //首先去掉title,就是没有title 那一行，但是还不是全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);    
        // 禁止屏幕休眠
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //去掉状态栏
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
        setContentView(R.layout.activity_borth);
        //监听开启短信监听按钮
        Button messgebutton=(Button) findViewById(R.id.button1);
            messgebutton.setOnClickListener(this);  
            
            
            mMp=MediaPlayer.create(this, R.raw.borth);
            // 声音文件初始化
            mMp.start();// 生音播放开始  
            // 声音播放的关联Listener设定
            mMp.setOnCompletionListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_borth, menu);
        return true;
    }
    public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent storyintent=new Intent();
		storyintent.setClass(BorthActivity.this,RemenberActivity.class);
		mMp.stop(); 
        startActivity(storyintent);
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
	}

	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Intent storyintent=new Intent();
		storyintent.setClass(BorthActivity.this,RemenberActivity.class);	
        startActivity(storyintent);
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
	}
	
	 @Override  
	    protected void onPause() {  
	        super.onPause();  
	        mMp.stop();
	    }  
	      
	    @Override  
	    protected void onStop() {  
	        super.onStop();  
	        mMp.stop();
	    }  
	  @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        mMp.stop();
	    }  
}
