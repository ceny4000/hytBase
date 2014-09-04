package com.hyt.base.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 * Title: EMailUtils 自動寄信Utils
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/29
 */
public class EMailUtils {
	public static String DEFAULT_HOST = "smtp.googlemail.com";

	/**
	 * 寄送郵件 - 多人
	 * @param host
	 * @param username
	 * @param password
	 * @param from
	 * @param subject
	 * @param msg
	 * @param tos
	 * @throws Exception
	 */
	public static void sendMail(String host, String username, String password, String from, String subject, String msg, List<String> tos) throws Exception {
		Email email = new SimpleEmail();
		email.setHostName(host);
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setSSLOnConnect(true);
		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(msg);
		for (String to : tos) {
			email.addTo(to);
		}
		email.send();
	}
	
	/**
	 * 寄送郵件 - 單人
	 * @param host
	 * @param username
	 * @param password
	 * @param from
	 * @param subject
	 * @param msg
	 * @param tos
	 * @throws Exception
	 */
	public static void sendMail(String host, String username, String password, String from, String subject, String msg, String to) throws Exception {
		List<String> arr = new ArrayList<String>();
		arr.add(to);
		sendMail(host, username, password, from, subject, msg, arr);
	}
}
