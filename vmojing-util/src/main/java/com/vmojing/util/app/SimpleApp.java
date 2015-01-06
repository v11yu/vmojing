package com.vmojing.util.app;

import java.util.Date;

/**
 * 运行app
 * 
 * @author v11
 */
public abstract class SimpleApp {
	public static final long DefaultDelayTime = 1000*60*5;
    private final long delay;
    private long lastTime = -1;//上次成功时间
    private int endCnt;//运行多少次后结束
    protected boolean ok = true;//运行状态
    /**
     * App构造函数
     * @param delay 每个操作的时间间隔
     * @param endCnt 操作多少次程序结束
     */
    public SimpleApp(long delay,int endCnt){
    	this.delay = delay;
    	
    }
    public void run(){
    	while(ok){
    		if(lastTime == -1 || getCurrentTime()-lastTime>delay){
    			lastTime = getCurrentTime();
    			work();
    		}else{
    			sleep(1000*60);
    		}
    	}
    }
    abstract protected void work();
    protected long getCurrentTime(){
    	return new Date().getTime();
    }
    protected void sleep(long sleepTime){
    	try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
