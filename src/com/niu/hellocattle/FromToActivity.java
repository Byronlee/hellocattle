package com.niu.hellocattle;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;


public class FromToActivity extends Activity  implements OnCompletionListener , SeekBar.OnSeekBarChangeListener{
	SeekBar mSeekBar;
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
    
       setContentView(R.layout.activity_from_to);
       
       mSeekBar = (SeekBar)findViewById(R.id.seek);
       mSeekBar.setOnSeekBarChangeListener(this);
       
       
       
       mMp=MediaPlayer.create(this, R.raw.yuanfengjing);
       // 声音文件初始化
       mMp.start();// 生音播放开始  
       // 声音播放的关联Listener设定
       mMp.setOnCompletionListener(this);
	}
        

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_from_to, menu);
        return true;
    }


    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
       // mProgressText.setText(progress + " " + getString(R.string.seekbar_text));   
    	
    	
    	//Toast.makeText(FromToActivity.this, progress, Toast.LENGTH_SHORT).show();
    	
    	
    	if(progress==87){
    		Toast.makeText(FromToActivity.this, "你好色额，居然偷亲我！好吧，给你讲一个爱情故事", Toast.LENGTH_LONG).show();
    		Intent storyintent=new Intent();
    		storyintent.setClass(FromToActivity.this,StoryActivity.class);
    		mMp.stop();   		
	        startActivity(storyintent);
	        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    	}
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    	
    	Toast.makeText(FromToActivity.this, "你要干啥？不准跑过来亲我哈！", Toast.LENGTH_SHORT).show();
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        //mTrackingText.setText(getString(R.string.seekbar_tracking_off));
    }


	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub	
		Intent storyintent=new Intent();
		storyintent.setClass(FromToActivity.this,StoryActivity.class);
		 mMp.stop();   		
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
