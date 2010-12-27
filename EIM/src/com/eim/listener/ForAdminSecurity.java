package com.eim.listener;
	
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
			loginTime = Calendar.getInstance();
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		//获得配置文件
		pu = new PropertiesUtil("admin.properties");
		//获取管理员登陆的时间
		loginOutTime = Calendar.getInstance();
		//将管理员在线的时间计算出来,并组装成一个字符串
		onlineTimeStr =getSeltaT(loginOutTime , loginTime);
		
		//以下代码修改管理员的在线时间
		String p_onlineTime = pu.read("onlinetime");
		//将管理员的在线时间更新到配置文件中
		pu.write("onlinetime", getOnlineTime(p_onlineTime,onlineTimeStr));
		//将管理员最后登录的时间记录到配置文件当中
		pu.write("lastaccesstime", new Date().toString());
		
	}
	
	private String getSeltaT(Calendar t1 , Calendar t2)
	{
		//该方法用于返回两个时间的差的小时，分钟和秒数的差距,t1-t2
		//返回的字符串的格式为：xx小时XX分钟xx秒
		String time = (t1.get(Calendar.HOUR)-t2.get(Calendar.HOUR))+"小时"
		+(t1.get(Calendar.MINUTE)-t2.get(Calendar.MINUTE))+"分钟"
		+(t1.get(Calendar.SECOND)-t2.get(Calendar.SECOND));
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
			}
			
			if(srcT[1]+addedT[1]>=60)
			{
				srcT[1]=srcT[1]+addedT[1]-60;
				srcT[0]=srcT[0]+1;
			}
		srcT[0] = srcT[0]+addedT[0];
		
		return srcT[0]+"小时"+srcT[1]+"分钟"+srcT[2]+"秒";
	}
	
	private int[] parseTime(String t)
	{
		int[] i = new int[3];
		StringTokenizer st = new StringTokenizer(t);
		i[0] = Integer.parseInt(st.nextToken("小时"));
		i[1] = Integer.parseInt(st.nextToken("分钟").replaceAll("小时", ""));
		i[2] = Integer.parseInt(st.nextToken("秒").replaceAll("分钟", ""));
		return i;
	}
	
	
	
}
