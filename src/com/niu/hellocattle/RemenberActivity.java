package com.niu.hellocattle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RemenberActivity extends Activity implements OnCompletionListener{
	
 
    boolean isStop=true;
    Timer timer = new Timer();  
    
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
    
        setContentView(R.layout.activity_remenber);
        
        final TextView rmber=(TextView) findViewById(R.id.textView1);   
        
        //相当于初始化
        String re_content_init= getCurTime();
        rmber.setText(re_content_init);
        
        
        
        
        final Handler handler = new Handler(){  
        	  
            public void handleMessage(Message msg) {  
                switch (msg.what) {      
                case 1:      
                	 String re_content= getCurTime();
                  //   Log.v("是否计算正确", re_content);

                     rmber.setText(re_content);
                    break;      
                }      
                super.handleMessage(msg);  
            }  
              
        };  
        
        TimerTask task = new TimerTask(){  
        	  
            public void run() {  
            	
            	while(isStop)
            		{
            		try
            		{
            			Thread.sleep(1000);	
                        Message message = new Message();      
                        message.what = 1;      
                        handler.sendMessage(message);    
                    } catch (InterruptedException e)
    		        {
		            	e.printStackTrace();
	             	}
		} 
              
        } 
        
        }   ;
        mMp=MediaPlayer.create(this, R.raw.haiyouwo);
        // 声音文件初始化
        mMp.start();// 生音播放开始  
        // 声音播放的关联Listener设定
        mMp.setOnCompletionListener(this);  
        timer.schedule(task, 1000);     
}
/**
         * 获得系统的当前时间
         * @param dateFormate 时间格式
         * @return
         */
        public static String getCurTime(){
        	
        	String rember="";
        	
        	try {
        		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		String begin = "2011-07-06 8:30:10";
        		
        		Date date = dateformat.parse(begin);       		       		
        		long BeginTime = date.getTime(); // date转成毫秒  开始时间
        		
        		Date nowTime = new Date();       		
        		long nowtime=nowTime.getTime();  //现在时间
        		
        		long alltime=(nowtime-BeginTime)/1000;  //之间一共的时间  s
        		

        		long year = alltime/ 3600 / 24/365;  //转换成年数 		
        		long Day = (alltime-(year*365*24*3600))/ 3600 / 24; //剩的天数   		
        		long hour = (alltime-(year*365*24*3600+Day*24*3600))/3600;  //剩的小时数       		
        		long min = (alltime-(year*365*24*3600+Day*24*3600+hour*3600))/60; //剩的分钟数
        		long sencond = alltime-(year*365*24*3600+Day*24*3600+hour*3600+min*60);//剩的秒数	
        		//合计        		
        		long allyear=alltime/ 3600 / 24/365;
        		long allday=alltime/ 3600 / 24;
        		long allhour=alltime/ 3600;
        		long allmin=alltime/60;
        		long allsencond =alltime; 		
        		
        	   rember="Tiangerniu , We have been in love for\n"+
        			year+"年  "+Day+"天   "+hour+"小时   "+min+"分钟  "+sencond+"秒\n"+
        			       "//合计" +
        			       " \n "+allyear+"年\n"+
        			       allday+"天\n" +
        			       allhour+"时\n" +
        			       allmin+"分\n" +
        			       allsencond+"秒\n" +"Love u forever and ever   --Byronlee\n\n"+"亲爱的，不管未来怎样让我们一起在时间的滴答声中一起慢慢变老....慢慢变老...慢慢表老...";
        	} catch (ParseException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        	return rember;	
        }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_remenber, menu);
        return true;
    }
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		secondeMedio seconde=new secondeMedio();
		seconde.star();
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
	
	class secondeMedio implements OnCompletionListener{
		secondeMedio(){
		 mMp=MediaPlayer.create(RemenberActivity.this, R.raw.dida);
	        // 声音文件初始化
	        
	        // 声音播放的关联Listener设定
	        mMp.setOnCompletionListener(this);  
		}
		
		void star(){
			 mMp.start();// 生音播放开始 
		}
		
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			mMp.start();
		}
		
	}
}
