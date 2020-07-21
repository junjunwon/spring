package co.kr.service;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import co.kr.controller.MemberController;
import co.kr.dao.MemberDAO;
import co.kr.vo.MemberVO;

@Service
public class UserMailSendServiceImpl implements UserMailSendService{
	
	
	@Inject
	MemberDAO dao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private MemberDAO memberDao;
	
	@Override
	public String init() throws Exception {
		
		Random ran=new Random();
		StringBuffer sb=new StringBuffer();
		int num=0;
		
		do {
			num=ran.nextInt(75)+48;
			if((num>=48&&num<=57)||(num>=65&&num<=90) || (num>=97&&num<=122)) {
				sb.append((char)num);
			}else {
				continue;
			}
		} while (sb.length()<size);
		
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}
	
	//난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;
	
	@Override
	public String getKey(boolean lowerCheck, int size) throws Exception {
		// TODO Auto-generated method stub
		this.lowerCheck=lowerCheck;
		this.size=size;
		return init();
	}
	
	//회원가입 발송 이메일(인증키 발송)
	@Override
	public String mailSendWithUserKey(MemberVO vo, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String key=getKey(false, 20);
		vo.setAuthKey(key);
		System.out.println("service mailsendwithuserkey에 있는 key는 : "+vo.getAuthKey());
		dao.GetKey(vo);
		System.out.println("service mailsendwithuserkey에 있는 userId : "+vo.getUserId());
		MimeMessage mail=mailSender.createMimeMessage();
		String htmlStr= "<h2>안녕하세요 MS :p 뭐먹을까 -직장인편- 고객센터 입니다!</h2><br><br>" 
				+ "<h3>" + vo.getUserId() + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://172.16.105.41:8080" + request.getContextPath() + "/member/keyAlter?user_id="+ vo.getUserId() +"&user_key="+vo.getAuthKey()+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)"
				+ "인증번호 : "+vo.getAuthKey();
		try {
			System.out.println("이메일 보내는 try로 진입.");
			System.out.println("request.getContextPath() : "+request.getContextPath());
			mail.setSubject("[본인인증] MS : p 뭐먹을까 -직장인편- 고객센터님의 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(vo.getUserEmail()));
			mailSender.send(mail);
			return vo.getAuthKey();
			
		} catch (MessagingException e) {
			// TODO: handle exception
			System.out.println("catch error");
			e.printStackTrace();
			return "catch error";
		}
		
	}
	@Override
	public void keyAlterConfirm(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("keyAlterconfirm vo : "+vo);
		dao.alterUserKey(vo);
		
	}
}
