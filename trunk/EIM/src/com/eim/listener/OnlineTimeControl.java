package com.eim.listener;

import javax.servlet.http.HttpSessionEvent;

public class OnlineTimeControl implements Runnable{
	
	
	private HttpSessionEvent se;
	public OnlineTimeControl(HttpSessionEvent se)
	{
		this.se= se;
	}
	
	public void run() {
		try {
			Thread.sleep(30*60*1000);
			
			se.getSession().invalidate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
