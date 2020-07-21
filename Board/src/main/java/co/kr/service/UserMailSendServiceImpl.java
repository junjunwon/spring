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
	
	//������ �̿��� Ű ����
	private boolean lowerCheck;
	private int size;
	
	@Override
	public String getKey(boolean lowerCheck, int size) throws Exception {
		// TODO Auto-generated method stub
		this.lowerCheck=lowerCheck;
		this.size=size;
		return init();
	}
	
	//ȸ������ �߼� �̸���(����Ű �߼�)
	@Override
	public String mailSendWithUserKey(MemberVO vo, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String key=getKey(false, 20);
		vo.setAuthKey(key);
		System.out.println("service mailsendwithuserkey�� �ִ� key�� : "+vo.getAuthKey());
		dao.GetKey(vo);
		System.out.println("service mailsendwithuserkey�� �ִ� userId : "+vo.getUserId());
		MimeMessage mail=mailSender.createMimeMessage();
		String htmlStr= "<h2>�ȳ��ϼ��� MS :p �������� -��������- ������ �Դϴ�!</h2><br><br>" 
				+ "<h3>" + vo.getUserId() + "��</h3>" + "<p>�����ϱ� ��ư�� �����ø� �α����� �Ͻ� �� �ֽ��ϴ� : " 
				+ "<a href='http://172.16.105.41:8080" + request.getContextPath() + "/member/keyAlter?user_id="+ vo.getUserId() +"&user_key="+vo.getAuthKey()+"'>�����ϱ�</a></p>"
				+ "(Ȥ�� �߸� ���޵� �����̶�� �� �̸����� �����ϼŵ� �˴ϴ�)"
				+ "������ȣ : "+vo.getAuthKey();
		try {
			System.out.println("�̸��� ������ try�� ����.");
			System.out.println("request.getContextPath() : "+request.getContextPath());
			mail.setSubject("[��������] MS : p �������� -��������- �����ʹ��� ���������Դϴ�", "utf-8");
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
