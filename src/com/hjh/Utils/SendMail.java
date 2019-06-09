package com.hjh.Utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

			// �����˵������˺��磺xxx@163.com
			public static String sendEmailAccount = "931387378@qq.com";
			// �����˵��������Ȩ��(�Լ�������������п���������)
			public static String sendEmailPassword = "xivptlhozxmzbdjb";
			// �����������SMTP��������ַ���磺smtp.163.com
			public static String sendEmailSMTPHost = "smtp.qq.com";
			// �ռ��˵������˺�
			public static String receiveMailAccount = "";
		 
			// �ѷ����ʼ���װΪ����������Ϊ�ռ��˵������˺ź�Ҫ���͵�����
			public void sendMail(String receiveMailAccount, String mailContent) {
				// �������������ʼ��������Ĳ�������
				Properties props = new Properties();
				// ����ʹ��SMTPЭ��
				props.setProperty("mail.transport.protocol", "smtp");
				// ���÷����˵�SMTP��������ַ
				props.setProperty("mail.smtp.host", sendEmailSMTPHost);
				// ������Ҫ��֤
				props.setProperty("mail.smtp.auth", "true");
		 
				// �������ô����Ự����, ���ں��ʼ�����������
				Session session = Session.getInstance(props);
				// ����debugģʽ�����ڲ鿴���͹�������������־
				session.setDebug(true);
		 
				try {
					// ����һ���ʼ�
					MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount,mailContent);
		 
					// ���� Session ��ȡ�ʼ��������
					Transport transport = session.getTransport();
		 
					transport.connect(sendEmailAccount, sendEmailPassword);
		 
					// �����ʼ�, �������е��ռ���ַ, ͨ��message.getAllRecipients() ���Ի�ȡ���ڴ����ʼ�����ʱ��ӵ������ռ���
					transport.sendMessage(message, message.getAllRecipients());
		 
					// �ر�����
					transport.close();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
			/**
			 * 
			 * @param session
			 *            �ͷ����������ĻỰ
			 * @param sendMail
			 *            ����������
			 * @param receiveMail
			 *            �ռ�������
			 * @return
			 * @throws Exception
			 */
			public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,
					String mailContent) throws Exception {
				// ����һ���ʼ�
				MimeMessage message = new MimeMessage(session);
		 
				// ���÷����������ͱ����ʽ
				message.setFrom(new InternetAddress(sendMail, "��ҵ���¹���ϵͳ", "UTF-8"));
		 
				// �ռ���
				message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "�𾴵��û�", "UTF-8"));
		 
				// �����ʼ�����
				message.setSubject("�һ���������", "UTF-8");
		 
				// �����ʼ�����
				message.setContent(mailContent, "text/html;charset=UTF-8");
		 
				// ���÷���ʱ��
				message.setSentDate(new Date());
		 
				// ��������
				message.saveChanges();
		 
				return message;
			}

}
