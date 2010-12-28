package com.eim.listener;
	
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.*;

import com.eim.util.PropertiesUtil;
	
public class ForAdminSecurity implements HttpSessionListener{
	
	private	PropertiesUtil pu;
	private Calendar loginTime;
	private Calendar loginOutTime;
	private String onlineTimeStr;
		public void sessionCreated(HttpSessionEvent se) {
			//创建了会话的事件,开始记录用户的登录时间
	new Thread(new OnlineTimeControl(se)).start();
	loginTime = Calendar.getInstance();
}

public void sessionDestroyed(HttpSessionEvent se) {
	//获得配置文件
	pu = new PropertiesUtil("admin.properties");
	
	//获取管理员登陆的时间
	loginOutTime = Calendar.getInstance();
	
	//将管理员当前在线的时间计算出来,并组装成一个字符串
	onlineTimeStr =getSeltaT(loginOutTime , loginTime);
	
	//以下代码修改管理员的在线时间
	//将管理员的在线时间更新到配置文件中
	try {
			String srcOnlineTime = new String(pu.read("onlinetime").getBytes("ISO-8859-1"),"utf-8");
			pu.write("onlinetime", getOnlineTime(srcOnlineTime , onlineTimeStr));
			System.out.println("srcOnlineTime"+srcOnlineTime);
			System.out.println("onlineTimeStr"+onlineTimeStr);
			System.out.println("getOnlineTime(srcOnlineTime,onlineTimeStr)"+getOnlineTime(srcOnlineTime,onlineTimeStr));
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

	//将管理员最后登录的时间记录到配置文件当中
	pu.write("lastaccesstime", new Date().toString());
	
}
private String getSeltaT(Calendar t1 , Calendar t2)
{
	//该方法用于返回两个时间的差的小时，分钟和秒数的差距,t1-t2
	int t1_t2_h = t1.get(Calendar.HOUR)-t2.get(Calendar.HOUR);
	int t1_t2_m = t1.get(Calendar.MINUTE)-t2.get(Calendar.MINUTE);
	int t1_t2_s = t1.get(Calendar.SECOND)-t2.get(Calendar.SECOND);
	//处理秒小于0
	if(t1_t2_s<0)
	{
		t1_t2_s = 60-t1_t2_s;
		t1_t2_m=t1_t2_m-1;
	}
	//处理分小于0
	if(t1_t2_m<0)
	{
		t1_t2_m = 60-t1_t2_m;
		t1_t2_h = t1_t2_h-1;
	}
	//处理小时小于0
	if(t1_t2_h<0)
	{
		t1_t2_h = 24+t1_t2_m;
	}
	
	//返回的字符串的格式为：xxHXXMxxS
	String time = (t1.get(Calendar.HOUR)-t2.get(Calendar.HOUR))+"H"
				+(t1.get(Calendar.MINUTE)-t2.get(Calendar.MINUTE))+"M"
				+(t1.get(Calendar.SECOND)-t2.get(Calendar.SECOND))+"S";
	return time;
}
private String getOnlineTime(String srcTime ,String addedTime)
{
	int[] srcT = parseTime(srcTime);//获得时分秒的一个数组
	int[] addedT = parseTime(addedTime);
	if(srcT[2]+addedT[2]>=60)
	{
		srcT[2]=srcT[2]+addedT[2]-60;
		if(srcT[1]+1==60)
		{
			srcT[1]=0;
			srcT[0]=srcT[0]+1;//
		}
		else
		{
			srcT[1]=srcT[1]+1;
		}
	}else{srcT[2]=srcT[2]+addedT[2];}
	
	if(srcT[1]+addedT[1]>=60)
	{
		srcT[1]=srcT[1]+addedT[1]-60;
		srcT[0]=srcT[0]+1;
	}
	else
	{
		srcT[1]=srcT[1]+addedT[1];
	}
	srcT[0] = srcT[0]+addedT[0];
	
	return srcT[0]+"H"+srcT[1]+"M"+srcT[2]+"S";
}

private int[] parseTime(String t)
{
	if(t.equals(""))
		{
			t="0H0M0S";
		}
		int[] i = new int[3];
		StringTokenizer st = new StringTokenizer(t);
		
		i[0] = Integer.parseInt(st.nextToken("H"));
		i[1] = Integer.parseInt(st.nextToken("M").replaceAll("H", ""));
		i[2] = Integer.parseInt(st.nextToken("S").replaceAll("M", ""));
		
		return i;
}
	

}
