package com.eim.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private static PropertiesUtil pu = new PropertiesUtil("mail.properties");
   /**
    * 
    * 发送邮件
    * @param title  邮件标题
    * @param content  邮件内容
    * @param toaddress 邮件目的地
    */
	public void SendMail(String title,String content,String toaddress) 
   {
	   String account=pu.read("account");
	   String password=pu.read("password");
	   String sendaddress=pu.read("sendaddress");
	   String server=pu.read("server");
	   Properties props=new Properties();
       props.setProperty("mail.smtp.auth","true");
       //注意属性的写法
       props.setProperty("mail.transport.protocol", "smtp");
       Session session=Session.getDefaultInstance(props);
       session.setDebug(true);
		Message msg=new MimeMessage(session);
		try {
			msg.setSubject(title);
			msg.setText(content);
			msg.setFrom(new InternetAddress(sendaddress));
			Transport transport=session.getTransport();
			transport.connect(server, 25,account, password);
		   //设置要传输的信件以及发件目的地
			transport.sendMessage(msg, new Address[]{new InternetAddress(toaddress)});
			transport.close();
		} 
		
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally{
		
		}
   }
	public static void main(String[] args) {
		Mail m=new Mail();
		m.SendMail("cap", "dadw", "710982325@qq.com");
	}
}
