package com.niu.hellocattle;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener, OnCompletionListener {

	String username="";
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
    
        setContentView(R.layout.activity_login);    
      //监听开启短信监听按钮
        Button messgebutton=(Button) findViewById(R.id.btn_sure);
            messgebutton.setOnClickListener(this);
            mMp=MediaPlayer.create(this, R.raw.lang);
            // 声音文件初始化
            mMp.start();// 生音播放开始  
            // 声音播放的关联Listener设定
            mMp.setOnCompletionListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	  EditText  name=(EditText) findViewById(R.id.name);
	  
	  username=name.getText().toString().trim();
					
		if(username.equals("")){
			//Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
		new AlertDialog.Builder(LoginActivity.this).setTitle("智能辨别提示").setMessage("不给出谜底，你不能继续享用此软件额！")
		   .setPositiveButton("确定", null).show();
		}else{
			if(username.equals("牛田歌")){
				
				Toast.makeText(LoginActivity.this, "我们果然心有灵犀，你的谜底答对了，看看，Byronlee 给你准备了什么", Toast.LENGTH_LONG).show();			
				Intent storyintent=new Intent();
				storyintent.setClass(LoginActivity.this,FromToActivity.class);
				mMp.stop(); 
				finish(); 
    	        startActivity(storyintent);  	                       
    	      //添加界面切换效果，注意只有Android的2.0(SdkVersion版本号为5)以后的版本才支持  
    	      int version = Integer.valueOf(android.os.Build.VERSION.SDK);     
    	      if(version  >= 5) {     
    	           overridePendingTransition(R.anim.zoomin, R.anim.zoomout);  //此为自定义的动画效果，下面两个为系统的动画效果  
    	         //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
    	           //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);  
    	      }    
			
			}else{
			    
				new AlertDialog.Builder(LoginActivity.this).setTitle("智能辨别提示").setMessage("谜底错误，请给出正确的谜底才能享用额，如果不是该软件的所有者，请自行退出！")
				   .setPositiveButton("确定", null).show();
			}
		}
	}

	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		 mMp.start();
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
