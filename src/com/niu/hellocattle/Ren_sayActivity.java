package com.niu.hellocattle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ren_sayActivity extends Activity  implements OnClickListener{

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
    
        setContentView(R.layout.activity_ren_say);
        
      //监听开启短信监听按钮
        Button messgebutton=(Button) findViewById(R.id.button1);
            messgebutton.setOnClickListener(this);
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ren_say, menu);
        return true;
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent storyintent=new Intent();
		storyintent.setClass(Ren_sayActivity.this,BorthActivity.class);
		finish();
        startActivity(storyintent);       
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);     
         
	}
}
