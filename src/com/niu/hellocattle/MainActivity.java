package com.niu.hellocattle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity 
{
    
    private LoadingView main_imageview;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);       
        //强制全屏
        //首先去掉title,就是没有title 那一行，但是还不是全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);    
        // 禁止屏幕休眠
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //去掉状态栏
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
        setContentView(R.layout.main);
        main_imageview = (LoadingView)findViewById(R.id.main_imageview);
        initLoadingImages();
        new Thread()
        {
            @Override
            public void run()
            {
                main_imageview.startAnim();
                
                try {
					Thread.sleep(5000);				
					Intent loginintent=new Intent();
        	        loginintent.setClass(MainActivity.this,LoginActivity.class);
        	         finish();         	        
        	        startActivity(loginintent);
        	        
        	        
          	      //添加界面切换效果，注意只有Android的2.0(SdkVersion版本号为5)以后的版本才支持  
          	      int version = Integer.valueOf(android.os.Build.VERSION.SDK);     
          	      if(version  >= 5) {     
          	           overridePendingTransition(R.anim.zoomin, R.anim.zoomout);  //此为自定义的动画效果，下面两个为系统的动画效果  
          	         //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
          	           //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);  
          	      } 
        	        
        	        
        	        
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
            }
        }.start();
    }
    private void initLoadingImages()
    {
        int[] imageIds = new int[6];
        imageIds[0] = R.drawable.loader_frame_1;
        imageIds[1] = R.drawable.loader_frame_2;
        imageIds[2] = R.drawable.loader_frame_3;
        imageIds[3] = R.drawable.loader_frame_4;
        imageIds[4] = R.drawable.loader_frame_5;
        imageIds[5] = R.drawable.loader_frame_6;
        
        main_imageview.setImageIds(imageIds);
    }
}