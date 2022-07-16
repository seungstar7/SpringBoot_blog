package net.utility;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator{

	//사용하고자 하는 메일서버(POP3, SMTP)에서 인증받은 계정+비번 등록
	private PasswordAuthentication pa;

	public MyAuthenticator() {
		pa=new PasswordAuthentication("soldesk@pretyimo.cafe24.com", "soldesk6901");
	}//default constructor(기본생성자)

	
	//pa를 private으로 막아놓아서 getter가 필요한데 부모 클래스에 있음
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}//get end
	
	
}//class end